
app.controller('orderStatus-ctrl', function ($scope, $http) {
    $scope.items = [];
    $scope.orderDetails = [];
    $scope.pendings = [];
    $scope.confirms = [];
    $scope.deliverings = [];
    $scope.successfulls = [];
    $scope.deletes = [];

    $scope.initialize = function () {
        // load Order
        $http.get('/rest/orders/list').then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        });
        // load pending order
        $http.get('/rest/orders/list/pending').then(resp => {
            $scope.pendings = resp.data;
            $scope.pendings.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        })
        // load pending confirms
        $http.get('/rest/orders/list/confirm').then(resp => {
            $scope.confirms = resp.data;
            $scope.confirms.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        })
        // load pending delivering
        $http.get('/rest/orders/list/delivering').then(resp => {
            $scope.deliverings = resp.data;
            $scope.deliverings.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        })
        // load pending successfulls
        $http.get('/rest/orders/list/successfull').then(resp => {
            $scope.successfulls = resp.data;
            $scope.successfulls.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        })
        // load pending deletes
        $http.get('/rest/orders/list/delete').then(resp => {
            $scope.deletes = resp.data;
            $scope.deletes.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        })
    }

    $scope.changeStatus = function (id) {
        var item = $scope.items.find(o => o.id === id);
        if (item.status == 'PENDING') {
            item.status = 'CONFIRMED';
        } else if (item.status == 'CONFIRMED') {
            item.status = 'DELIVERING';
        } else if (item.status == 'DELIVERING') {
            item.status = 'SUCCESSFUL';
        }
        $http.put(`/rest/orders/status/update/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(o => o.id === id);
            $scope.items[index] = item;
            swal("congratulations!", "Xác nhận thành công!", "success");
            $scope.initialize();
        })
    };

    $scope.delStatus = function (id) {
        var item = $scope.items.find(o => o.id === id);
        item.status = item.status == 'DELETED'? 'PENDING' : 'DELETED';
        $http.put(`/rest/orders/status/update/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(o => o.id === id);
            $scope.items[index] = item;
            $scope.initialize();
            item.status == 'DELETED'? swal("success!", "Huỷ thành công!", "success") : swal("success!", "Phục hồi thành công!", "success");
        })
    };

    $scope.order_detail = function (id) {
        $http.get(`/rest/orderDetail/list/${id}`).then(resp => {
            $scope.orderDetails = resp.data;
        });

    }

    $scope.deleteProduct = function (id) {
        var index = $scope.orderDetails.findIndex(oD => oD.id === id);
        $scope.orderDetails.splice(index, 1);
    }

    $scope.change_order = function (item) {
        if (item.address.trim().length === 0) {
            swal({
                title: "Error!",
                text: "Vui Lòng Nhập Địa Chỉ!",
                type: "error",
                confirmButtonText: "OK"
              });
              
        $scope.initialize();
        } else {
            console.log(item)
            $http.put(`/rest/orders/status/update/${item.id}`, item).then(resp => {
                let index = $scope.items.findIndex(o => o.id === item.id);
                $scope.items[index] = item;
                $scope.initialize();
                swal("congratulations!", "Bạn đã thay đổi địa chỉ!", "success");
            })
        }
    }
    $scope.initialize();
});