import axios from "axios";

const USER_BASE_REST_API_URL = "http://localhost:8765/user-service/users"

class UserService {
    getUsers() {
        return axios.get(USER_BASE_REST_API_URL)
    }

    saveUser(user) {
        return axios.post(USER_BASE_REST_API_URL, user)
    }

    getUserById(userId) {
        return axios.get(USER_BASE_REST_API_URL + "/" + userId)
    }

    updateUserById(userId, user) {
        return axios.put(USER_BASE_REST_API_URL + "/" + userId, user)
    }

    deleteUserById(userId) {
        return axios.delete(USER_BASE_REST_API_URL + "/" + userId)
    }
}

export default new UserService()