import axios from "axios";

const USER_BASE_REST_API_URL = "http://localhost:8765/user-service/users"

class UserService {
    getUsers() {
        var token = localStorage.getItem("jwt_token");
        return axios.get(USER_BASE_REST_API_URL,{headers: {Authorization: `Bearer ${token}`}})
    }

    getAccount(){
        var token = localStorage.getItem("jwt_token");
        return axios.get(USER_BASE_REST_API_URL + "/account",{headers: {Authorization: `Bearer ${token}`}})
    }

    getUserById(userId) {
        var token = localStorage.getItem("jwt_token");
        return axios.get(USER_BASE_REST_API_URL + `/${userId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }

    saveUser(user) {
        var token = localStorage.getItem("jwt_token");
        return axios.post(USER_BASE_REST_API_URL, user,{headers: {Authorization: `Bearer ${token}`}})
    }

    editUserById(userId, user) {
        var token = localStorage.getItem("jwt_token");
        return axios.put(USER_BASE_REST_API_URL + `/${userId}`, user,
            {headers: {Authorization: `Bearer ${token}`}})
    }

    deleteUserById(userId) {
        var token = localStorage.getItem("jwt_token");
        return axios.delete(USER_BASE_REST_API_URL + `/${userId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }
}

export default new UserService()