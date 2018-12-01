var request = new XMLHttpRequest();
var requestKey = new XMLHttpRequest();
var requestCart = new XMLHttpRequest();

function addToCart(event, id){

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
                    productDiv.appendChild(errorMessage);
                }
            }
        }
    }
    //user is not logged in
    else{
        window.location = "/store-2.0.3.RELEASE/html/login.html"
    }

    event.stopPropagation();
}

function searchByKeyword(keyword) {
    requestKey.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/items/search/' + keyword, true);
    requestKey.send();
    requestKey.onreadystatechange = function () {
        if(requestKey.readyState === 4) {
            if (requestKey.status >= 200 && requestKey.status < 400) {
                var data = JSON.parse(this.response);
                container.innerHTML = "";
                createCards(data, 100);
            } else {
                const errorMessage = document.createElement('marquee');
                errorMessage.textContent = `Error`;
                productDiv.appendChild(errorMessage);
            }
        }
    };
}

const productDiv = document.getElementById('productList');

const container = document.createElement('div');
container.setAttribute('class', 'container');

productDiv.appendChild(container);
request.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/items', true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(this.response);
            createCards(data, 10);
        } else {
            const errorMessage = document.createElement('marquee');
            errorMessage.textContent = `Error`;
            productDiv.appendChild(errorMessage);
        }
    }
};

function createCards(data, maxSize){
        for(var i=0; i < maxSize; i++) {
            let product = data[i];
            const card = document.createElement('div');
            card.setAttribute('class', 'card');
            card.setAttribute('onClick',`window.location = "/store-2.0.3.RELEASE/html/productDetails.html#"+${product.id}`);

            const h1 = document.createElement('h1');
            h1.textContent = product.name;

            const desc = document.createElement('p');
            product.description = product.desc;
            desc.textContent = `${product.desc}`;
            desc.setAttribute('class', 'desc');

            const size = document.createElement('p');
            product.description = product.size;
            size.textContent = `Size: ${product.size}`;

            const price = document.createElement('p');
            product.description += product.salePrice;
            price.textContent = `Price: $${product.salePrice}`;

            var button = document.createElement('input');
            button.setAttribute('type', 'button');
            button.setAttribute('class', 'btn btn-success cart');
            button.setAttribute('value', 'Add to Cart');
            button.setAttribute('onclick', `addToCart(event,${product.id})`);

            container.appendChild(card);
            card.appendChild(h1);
            card.appendChild(desc);
            card.appendChild(size);
            card.appendChild(price);
            card.appendChild(button);
        }
}

request.send();