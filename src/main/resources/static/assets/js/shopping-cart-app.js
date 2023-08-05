const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {
    /**
     *                   Quản Lý Giỏ Hàng
     */
    $scope.cart = {
        items: [],
    //    Thêm sản phẩm vào giỏ
        add(id){
            var item = this.items.find(item => item.id == id);
            if(item) {
                item.quantity++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp =>{
                    resp.data.quantity = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
    //    Xóa sp khỏi giỏ hàng b
        remove(id){
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index,1);
            this.saveToLocalStorage();
        },

    //    Xóa sạch sp trong giỏ
        clear(){
            this.items = [];
            this.saveToLocalStorage();
        },

    //    Tính tỏng tiền của 1 sp
        amt_of(item){},

    //    Tính all số lượng sp trong giỏ
        get count(){
            return this.items
                .map(item => item.quantity)
                .reduce((total, quantity) => total += quantity, 0);
        },

    // tính All thiền các mặt hàng
        get amount() {
            return this.items
                .map(item=> item.quantity * item.price)
                .reduce((total, quantity) => total += quantity, 0);
         },

    //    Lưu giỏ hàng vào local storage
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

    //    Đọc giỏ hàng từ local storage
        loadFromLocalStorage(){

            var json = localStorage.getItem("cart");
            this.items = json? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate: new Date(),
        address: "",
        account:{username: $("#username").text()},
        get orderDetails(){
            return $scope.cart.items.map(item => {
                return {
                    product:{id: item.id},
                    price: item.price,
                    quantity: item.quantity
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            // thực hiện đặt hàng
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công!");
                $scope.cart.clear();// xóa giỏ hàng
                location.href = "/order/detail/" + resp.data.id;// chuyển sang deltail với mã đơn hàng vừa dặt
            }).catch(err => {
                alert("Đặt hàng lỗi!");
                console.log(err)
            })
            alert("Đặt hàng");
        }
    }
})