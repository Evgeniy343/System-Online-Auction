import axios from "axios";

const CARD_BASE_REST_API_URL = "http://localhost:8765/card-service/cards"

class CardService {
    getCards() {
        return axios.get(CARD_BASE_REST_API_URL)
    }

    getUserCards() {
        return axios.get(CARD_BASE_REST_API_URL + "/my")
    }

    saveCard(card) {
        return axios.post(CARD_BASE_REST_API_URL, card)
    }

    getCardById(cardId) {
        return axios.get(CARD_BASE_REST_API_URL + "/" + cardId)
    }

    updateCardById(cardId, card) {
        return axios.put(CARD_BASE_REST_API_URL + "/" + cardId, card)
    }

    deleteCardById(cardId) {
        return axios.delete(CARD_BASE_REST_API_URL + "/" + cardId)
    }
}

export default new CardService()