import axios from "axios";

const AUCTION_BASE_REST_API_URL = "http://localhost:8765/auction-service/auctions"

class AuctionService {
    getAuctions() {
        var auctions = []
        if (localStorage.getItem("jwt_token") === null){
            console.log("No token")
            auctions = axios.get(AUCTION_BASE_REST_API_URL)
        } else {
            var token = localStorage.getItem("jwt_token");
            console.log("token - " + token)
            auctions = axios.get(AUCTION_BASE_REST_API_URL, { headers: {Authorization : `Bearer ${token}`}})
        }
        console.log(auctions)
        return auctions
    }

    getUserAuctions() {
        var myAuctions = axios.get(AUCTION_BASE_REST_API_URL + "/my")
        console.log(myAuctions)
        return myAuctions
    }

    saveAuction(auction) {
        return axios.post(AUCTION_BASE_REST_API_URL, auction)
    }

    getAuctionById(auctionId) {
        return axios.get(AUCTION_BASE_REST_API_URL + "/" + auctionId)
    }

    updateAuctionById(auctionId, auction) {
        return axios.put(AUCTION_BASE_REST_API_URL + "/" + auctionId, auction)
    }

    deleteAuctionById(auctionId) {
        return axios.delete(AUCTION_BASE_REST_API_URL + "/" + auctionId)
    }
}

export default new AuctionService()