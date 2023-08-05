app.controller("authority-ctrl", function($scope, $http, $location){
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function() {
        // Load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })
        // Load staffs and directors (administrators)
        $http.get('/rest/accounts?admin=true').then(resp => {
            $scope.admins = resp.data;
        });

        // Load authorities of staffs and directors
        $http.get('/rest/authorities?admin=true').then(resp => {
            $scope.authorities = resp.data;
        }).catch(err =>{
            $location.path('unauthorized');
        });
    }

    $scope.authority_of = function(acc, role) {
        if($scope.authorities) {
            return $scope.authorities.find(ur => ur.account.username == acc.username
                 && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function(acc, role) {
        var authority = $scope.authority_of(acc, role);
        if(authority) {// đã cấp quyền => thu hồi (xóa)
            $scope.revoke_authority(authority);
        } else {// chưa cấp quyền => cấp quyền (add)
            authority = {
                account: acc,
                role: role
            };
            $scope.grant_authority(authority);
        }
    }
    // thêm mới authority
    $scope.grant_authority = function(authority) {
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data);
            swal("congratulations!", 'Cấp quyền sử dụng thành công!', "success");
        }).catch(err => {
            swal("Error!", 'Cấp quyền sử dụng thất bại!', "error");
            console.log('err',err);
        })
    }
    //  xóa authority
    $scope.revoke_authority = function(authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            swal("congratulations!", 'Thu hồi quyền sử dụng thành công!', "success");
        }).catch(err => {
            swal("Error!", 'Thu hồi quyền thất bại!', "error");
            console.log('Error',err);
        })
    }
    $scope.initialize();
})