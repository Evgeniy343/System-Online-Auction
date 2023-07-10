import axios from "axios";

const CARD_BASE_REST_API_URL = "http://localhost:8765/card-service/cards"

class CardService {
    getCards() {
        var token = localStorage.getItem("jwt_token");
        return axios.get(CARD_BASE_REST_API_URL,{headers: {Authorization: `Bearer ${token}`}})
    }

    saveCard(card) {
        var token = localStorage.getItem("jwt_token");
        return axios.post(CARD_BASE_REST_API_URL, card, {headers: {Authorization: `Bearer ${token}`}})
    }

    getCardById(cardId) {
        var token = localStorage.getItem("jwt_token");
        return axios.get(CARD_BASE_REST_API_URL + `/${cardId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }

    editCardById(cardId, card) {
        var token = localStorage.getItem("jwt_token");
        return axios.put(CARD_BASE_REST_API_URL + `/${cardId}`, card,
            {headers: {Authorization: `Bearer ${token}`}})
    }

    deleteCardById(cardId) {
        var token = localStorage.getItem("jwt_token");
        return axios.delete(CARD_BASE_REST_API_URL + `/${cardId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }
}

export default new CardService()