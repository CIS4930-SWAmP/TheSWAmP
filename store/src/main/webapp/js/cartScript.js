var request = new XMLHttpRequest();
var requestCheck = new XMLHttpRequest();
var requestItems = new XMLHttpRequest();
var remove = new XMLHttpRequest();
var subtotal = 0;
var cartId = -1;
const username = document.cookie.substr(document.cookie.indexOf('=')+1);

request.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/carts?username='+username, true);
request.onload = function () {
    if(username===""){
        window.location = 'http://localhost:8080/store-2.0.3.RELEASE/html/login.html';
    }
    const productDiv = document.getElementById('cart');
    if(request.readyState === 4) {
        if (request.status === 200) {
            cartId = this.response.substr(12,this.response.indexOf(',')-12);
            requestItems.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/carts/'+username,true);
            requestItems.send();
            requestItems.onreadystatechange = function () {
                if (requestItems.readyState === 4) {
                    console.log("hello");
                    if (requestItems.status === 200) {
                        console.log("hi");
                        var data = JSON.parse(requestItems.response);
                        data.forEach(product => {
                            const shell = document.createElement('div');
                            shell.setAttribute('class','border border-dark');
                            shell.setAttribute('style','margin:5px;padding:5px 0 50px 0;');

                            productDiv.appendChild(shell);

                            const h1 = document.createElement('h3');
                            h1.textContent = product.name;

                            const price = document.createElement('h4');
                            price.textContent = `$${product.salePrice}`;

                            var button = document.createElement('input');
                            button.setAttribute('type', 'button');
                            button.setAttribute('class', 'btn btn-danger');
                            button.setAttribute('value', 'Remove From Cart');
                            button.setAttribute('onclick', `removeFromCart(event,${product.id})`);
                            button.setAttribute('style', 'margin:10px;float:right');


                            shell.appendChild(h1);
                            shell.appendChild(price);
                            shell.appendChild(button);

                            subtotal += product.salePrice;
                        });
                        document.getElementById("sub").innerText = "Subtotal: $"+subtotal.toFixed(2);
                        document.getElementById("tax").innerText = "Total(After Tax): $"+(subtotal * 1.08).toFixed(2);
                    }
                }
            }
        } else {
            var msg = document.createElement('h4');
            msg.innerText = "Cart is empty.";
            msg.setAttribute('style', 'text-align:center');
            productDiv.appendChild(msg);
        }
    }
};

function checkout() {
    requestCheck.open('PUT', 'http://localhost:8080/store-2.0.3.RELEASE/store/carts/purchase/'+cartId, true);
    requestCheck.send();
    requestCheck.onreadystatechange = function() {
        if (requestCheck.readyState === 4) {
            if (requestCheck.status === 200) {
                location.reload(true);
            }
        }
    }
}

function removeFromCart(event, id){
    remove.open('DELETE','http://localhost:8080/store-2.0.3.RELEASE/store/carts?cartId='+cartId+'&productId='+id, true);
    remove.send();
    remove.onreadystatechange = function() {
        if(remove.readyState === 4){
            if(remove.status === 200){
                location.reload(true);
            }
        }
    };

    event.stopPropagation();
}
request.send();