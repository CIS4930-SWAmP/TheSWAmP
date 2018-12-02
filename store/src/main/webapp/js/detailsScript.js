var request = new XMLHttpRequest();
var requestCart = new XMLHttpRequest();
var url = window.location.href;
var itemId = url.substr(url.indexOf('#')+1,url.length - url.indexOf('#')-1);

function addToCart1(id){

    const username = document.cookie.substr(document.cookie.indexOf('=')+1);

    if(username!== ''){
        requestCart.open('POST', 'http://localhost:8080/store-2.0.3.RELEASE/store/carts?productId='+id+'&username='+username, true);
        requestCart.send();
        requestCart.onreadystatechange = function () {
            if (requestCart.readyState === 4) {
                if (requestCart.status === 200) {
                    alert('Item added to cart');
                }
                else {
                    const errorMessage = document.createElement('marquee');
                    errorMessage.textContent = `Error`;
                    eventDiv.appendChild(errorMessage);
                }
            }
        }
    }
    //user is not logged in
    else{
        window.location = "../index.html"
    }
    //event.stopPropagation();
}

request.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/items/'+itemId, true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status === 200) {
                var data = JSON.parse(this.response);
                document.getElementById('name').innerHTML = data.name;
                const productDiv = document.getElementById('details');

                const h1 = document.createElement('h1');
                h1.textContent = data.name;

                const price = document.createElement('h4');
                price.textContent = `$${data.salePrice}`;

                const desc = document.createElement('p');
                desc.textContent = `${data.desc}`;

                const brand = document.createElement('p');
                brand.textContent = `Brand: ${data.brand}`;

                const color = document.createElement('p');
                color.textContent = `Color: ${data.color}`;

                const size = document.createElement('p');
                size.textContent = `Size: ${data.size}`;

                const gender = document.createElement('p');
                gender.textContent = `Gender: ${data.gender}`;

                const button = document.createElement('input');
                button.setAttribute('type', 'button');
                button.setAttribute('class', 'btn btn-success cart');
                button.setAttribute('value', 'Add to Cart');
                button.setAttribute('onclick', 'addToCart1('+itemId+')');
                // button.setAttribute('onclick', 'addToCart1('+eventId+')');
                button.setAttribute('style', 'float:right');

                productDiv.appendChild(h1);
                productDiv.appendChild(price);
                productDiv.appendChild(desc);
                productDiv.appendChild(brand);
                productDiv.appendChild(color);
                productDiv.appendChild(size);
                productDiv.appendChild(button);

        } else {
            const errorMessage = document.createElement('marquee');
            errorMessage.textContent = `Error`;
            eventDiv.appendChild(errorMessage);
        }
    }
};


request.send();
