var request = new XMLHttpRequest();
var requestKey = new XMLHttpRequest();
var requestCart = new XMLHttpRequest();

request.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/items', true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(this.response);
            createList(data);
        } else {
            const errorMessage = document.createElement('marquee');
            errorMessage.textContent = `Error`;
            productDiv.appendChild(errorMessage);
        }
    }
};

function searchByKeyword(keyword) {
    requestKey.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/items/search/' + keyword, true);
    requestKey.send();
    requestKey.onreadystatechange = function () {
        if(requestKey.readyState === 4) {
            if (requestKey.status >= 200 && requestKey.status < 400) {
                var data = JSON.parse(this.response);
                const table = document.getElementById('products');
                table.innerHTML = "";
                createList(data);
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

function createList(data){
    data.forEach(product => {
        const table =document.getElementById('products');
        const list = document.createElement('tr');
        list.setAttribute('onclick',`window.location = "/store-2.0.3.RELEASE/html/productDetails.html#"+${product.id}`);

        //columns
        const name = document.createElement('td');
        name.textContent = product.name;

        const desc = document.createElement('td');
        product.description = product.desc;
        desc.textContent = `${product.desc}`;
        desc.setAttribute('class', 'desc');

        const size = document.createElement('td');
        product.description = product.size;
        size.textContent = `Size: ${product.size}`;

        const price = document.createElement('td');
        product.description += product.salePrice;
        price.textContent = `Price: $${product.salePrice}`;

        const btnCol = document.createElement('td');

        var button = document.createElement('input');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-success cart');
        button.setAttribute('value', 'Add to Cart');
        button.setAttribute('onclick', `addToCart(event,${product.id})`);
        button.setAttribute('style', 'margin:10px;');

        btnCol.appendChild(button);

        //Append Columns to list
        list.appendChild(name);
        list.appendChild(desc);
        list.appendChild(size);
        list.appendChild(price);
        list.appendChild(btnCol);

        //Append list to table
        table.appendChild(list);

    });
}

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

request.send();