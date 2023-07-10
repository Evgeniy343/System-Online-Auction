import axios from "axios";

const BID_BASE_REST_API_URL = "http://localhost:8765/auction-service/bids"

class BidService {
    getBids() {
        var token = localStorage.getItem("jwt_token");
        var data = axios.get(BID_BASE_REST_API_URL, {headers: {Authorization: `Bearer ${token}`}})
        console.log(data)
        return data
    }

    getUserBids() {
        var token = localStorage.getItem("jwt_token");
        var data = axios.get(BID_BASE_REST_API_URL + "/my", {headers: {Authorization: `Bearer ${token}`}})
        console.log(data)
        return data
    }

    saveBid(auctionId, suggestedPrice) {
        var token = localStorage.getItem("jwt_token");
        return axios.post(BID_BASE_REST_API_URL + `?auctionId=${auctionId}&suggested_price=${suggestedPrice}`,
            null, {headers: {Authorization: `Bearer ${token}`}})
    }

    getBidById(bidId) {
        var token = localStorage.getItem("jwt_token");
        return axios.get(BID_BASE_REST_API_URL + `/${bidId}`, {headers: {Authorization: `Bearer ${token}`}})
    }

    editBidById(auctionId, suggestedPrice, bidId) {
        var token = localStorage.getItem("jwt_token");
        return axios.put(BID_BASE_REST_API_URL + `/${bidId}?auctionId=${auctionId}&suggested_price=${suggestedPrice}`,
            null, {headers: {Authorization: `Bearer ${token}`}})
    }

    deleteBidById(bidId) {
        var token = localStorage.getItem("jwt_token");
        return axios.delete(BID_BASE_REST_API_URL + `/${bidId}`,
            {headers: {Authorization: `Bearer ${token}`}})
    }
}

export default new BidService()