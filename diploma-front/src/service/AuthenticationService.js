import axios from "axios";

const AUTHENTICATION_BASE_REST_API_URL = "http://localhost:8765/authentication-service/auth"

class AuthenticationService {
    singUp(user) {
        console.log(user)
        var token = axios.post(AUTHENTICATION_BASE_REST_API_URL + "/signUp", user)
        return token
    }

    getToken(userData) {
        console.log(JSON.stringify(userData))
        var token = axios.post(AUTHENTICATION_BASE_REST_API_URL + "/token", userData)
        return token
    }
}

export default new AuthenticationService()