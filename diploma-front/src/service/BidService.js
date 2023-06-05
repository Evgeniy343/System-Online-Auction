import axios from "axios";

const BID_BASE_REST_API_URL = "http://localhost:8765/auction-service/bids"

class BidService {
    getBids() {
        var data = axios.get(BID_BASE_REST_API_URL)
        console.log(data)
        return data
    }

    getUserBids() {
        var data = axios.get(BID_BASE_REST_API_URL + "/my")
        console.log(data)
        return data
    }

    saveBid(bid) {
        return axios.post(BID_BASE_REST_API_URL, bid)
    }

    getBidById(bidId) {
        return axios.get(BID_BASE_REST_API_URL + "/" + bidId)
    }

    updateBidById(bidId, bid) {
        return axios.put(BID_BASE_REST_API_URL + "/" + bidId, bid)
    }

    deleteBidById(bidId) {
        return axios.delete(BID_BASE_REST_API_URL + "/" + bidId)
    }
}

export default new BidService()