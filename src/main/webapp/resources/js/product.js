getProduct(this.href.substr(this.href.lastIndexOf('/') + 1),
    function (resp) {
        console.log(resp);
    });