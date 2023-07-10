import axios from "axios";

const AUCTION_BASE_REST_API_URL = "http://localhost:8765/auction-service/auctions"

class AuctionService {
    getAuctions() {
        var auctions = []
        const token = localStorage.getItem("jwt_token")
        console.log("AuctionService TOKEN - " + token)
        if (!!token && token.trim() !== 'undefined') {
            // true
            console.log("AuctionService token is not null")
            auctions = axios.get(AUCTION_BASE_REST_API_URL, {headers: {Authorization: `Bearer ${token}`}})
        } else {
            // false
            console.log("AuctionService token is null")
            auctions = axios.get(AUCTION_BASE_REST_API_URL)
        }
        console.log(auctions)
        return auctions
    }

    getMyAuctions() {
        var token = localStorage.getItem("jwt_token");
        var myAuctions = axios.get(AUCTION_BASE_REST_API_URL + "/my",
            {headers: {Authorization: `Bearer ${token}`}})
        console.log(myAuctions)
        return myAuctions
    }

    saveAuction(auction) {
        var token = localStorage.getItem("jwt_token");
        return axios.post(AUCTION_BASE_REST_API_URL, auction, {headers: {Authorization: `Bearer ${token}`}})
    }

    getAuctionById(auctionId) {
        var token = localStorage.getItem("jwt_token");
        return axios.get(AUCTION_BASE_REST_API_URL + `/${auctionId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }

    updateAuctionById(auctionId, auction) {
        var token = localStorage.getItem("jwt_token");
        return axios.put(AUCTION_BASE_REST_API_URL + `/${auctionId}`, auction,
            {headers: {Authorization: `Bearer ${token}`}})
    }

    deleteAuctionById(auctionId) {
        var token = localStorage.getItem("jwt_token");
        return axios.delete(AUCTION_BASE_REST_API_URL + `/${auctionId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }
}

export default new AuctionService()