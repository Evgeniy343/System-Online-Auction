import axios from "axios";

const BASKET_BASE_REST_API_URL = "http://localhost:8765/auction-service/basket"

class BasketService {
    deleteAuctionFromBasket(auctionId) {
        // var token = localStorage.getItem("jwt_token");
        // return axios.delete(BASKET_BASE_REST_API_URL, {headers: {Authorization: `Bearer ${token}`}},
        // {auctionId: auctionId})
    }

    getBasket() {
        var token = localStorage.getItem("jwt_token");
        return axios.get(BASKET_BASE_REST_API_URL, {headers: {Authorization: `Bearer ${token}`}})
    }

    addAuctionToBasket(auctionId) {
        var token = localStorage.getItem("jwt_token");
        return axios.post(BASKET_BASE_REST_API_URL + `?auctionId=${auctionId}`,null,
            {headers: {Authorization: `Bearer ${token}`}},)
    }
}

export default new BasketService()