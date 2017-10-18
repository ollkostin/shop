function buildTableData(value, mapper) {
    let td = $('<td></td>');
    if (mapper) {
        td.append(mapper(value));
    } else {
        td.append(value);
    }
    return td;
}

function clearChildNodes(element) {
    element.empty()
}

function addToCart(e) {
    console.log('not implemented yet');
}