let currentPage = 0;
let currentSize = 10;

getProducts(currentPage, currentSize);


function showProducts(productList) {
    let products = document.getElementById('products');
    clearChildNodes(products);
    productList.forEach(el => {
        let tr = document.createElement('tr');
        tr.appendChild(buildElement(el['id'], 'td'));
        tr.appendChild(buildElement(el['name'], 'td'));
        tr.appendChild(buildElement(buildImg(el['pathToPhoto']), 'td'));
        tr.appendChild(buildElement(el['price'], 'td'));
        tr.appendChild(buildElement(cartButton(), 'td'));
        tr.onclick = e => {

        };
        products.appendChild(tr);
    });
}

function buildImg(pathToPhoto) {
    let img = document.createElement('img');
    img.alt = 'product image';
    return img;
}

function getProducts(page, size) {
    let options = page || size ? '?' : '';
    if (page)
        options += 'page=' + page;
    if (page && size)
        options += '&'
    if (size)
        options += 'size=' + size

    $.ajax({
        type: 'GET',
        url: 'api/product/' + options,
        success: resp => {
            showProducts(resp);
            currentPage = page;
            currentSize = size;
        },
        error: function (resp) {

        }
    });
}

function buildElement(value, htmlTag) {
    let tag = document.createElement(htmlTag);
    if (isElement(value)) {
        tag.appendChild(value);
    } else {
        tag.innerHTML = value;
    }
    return tag;
}

function clearChildNodes(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function cartButton() {
    let button = document.createElement('button');
    button.innerHTML = 'Add to cart';
    button.className = 'btn btn-success';
    button.onclick = addToCart;
    return button;
}

function onSizeChange() {
    let sizeSelect = document.getElementById("size-select");
    let newSize = sizeSelect.options[sizeSelect.selectedIndex].value;
    if (currentSize !== newSize) {
        currentSize = newSize;
        getProducts(currentPage, currentSize)
    }
}

function isElement(obj) {
    try {
        return obj instanceof HTMLElement;
    }
    catch (e) {
        return (typeof obj === "object") &&
            (obj.nodeType === 1) && (typeof obj.style === "object") &&
            (typeof obj.ownerDocument === "object");
    }
}

function addToCart(e) {
    console.log('not implemented yet');
}