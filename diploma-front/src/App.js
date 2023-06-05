import React from "react";
import "bootstrap/dist/css/bootstrap.min.css"
import Header from "./components/Header";
import Footer from "./components/Footer";
import ListAuctionComponent from "./components/list/ListAuctionComponent";
import Categories from "./components/Categories";
import AuctionService from "./service/AuctionService";
import ShowFullAuctionInfo from "./components/info/ShowFullAuctionInfo";
import AuthenticationService from "./service/AuthenticationService";
import BasketService from "./service/BasketService";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            auctions: [],
            auctionsInBasket: [],
            currentAuctions: [],
            showFullAuctionInfo: false,
            fullAuctionInfo: {}
        }
        this.onShowFullAuctionInfo = this.onShowFullAuctionInfo.bind(this)
        this.getAllAuctions = this.getAllAuctions.bind(this)
        this.addToBasket = this.addToBasket.bind(this)
        this.chooseCategory = this.chooseCategory.bind(this)
        // this.deleteAuction = this.deleteAuction.bind(this)
        this.signUp = this.signUp.bind(this)
        this.signIn = this.signIn.bind(this)
        this.signOut = this.signOut.bind(this)
        this.getBasket = this.getBasket.bind(this)
    }

    render() {
        return (
            <div className="wrapper">
                <Header onSignIn={this.signIn} onSignUp={this.signUp} auctions={this.state.auctionsInBasket}/>
                <Categories chooseCategory={this.chooseCategory}/>
                <ListAuctionComponent onShowFullAuctionInfo={this.onShowFullAuctionInfo}
                                      auctions={this.state.currentAuctions} onAdd={this.addToBasket}/>
                {this.state.showFullAuctionInfo && <ShowFullAuctionInfo
                    onAdd={this.addToBasket} onShowFullAuctionInfo={this.onShowFullAuctionInfo}
                    fullAuctionInfo={this.state.fullAuctionInfo}/>}
                <Footer/>
            </div>
    )
    }

    componentDidMount() {
        if(localStorage.getItem("jwt_token") != null){
            console.log("getBasket")
            this.getBasket()
        }
        this.getAllAuctions()
    }

    onShowFullAuctionInfo(auction) {
        this.setState({fullAuctionInfo: auction})
        this.setState({showFullAuctionInfo: !this.state.showFullAuctionInfo})
    }

    chooseCategory(category) {
        if (category === 'ALL') {
            this.setState({currentAuctions: this.state.auctions})
            return
        }
        this.setState({
            currentAuctions: this.state.auctions.filter(el => el.product.category === category)
        })
    }

    // deleteAuctionFromBasket(id) {
    //     BasketService.deleteAuctionFromBasket(auctionId).then((response) => {
    //         localStorage.setItem("jwt_token", response.data)
    //         console.log(response.data)
    //     }).catch(error => {
    //         console.log(error)
    //     })
    // }

    getBasket(){
        BasketService.getBasket().then((response) => {
            this.setState({auctionsInBasket: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    addToBasket(auctionId) {
        console.log(auctionId)
        BasketService.addAuctionToBasket(auctionId).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    getAllAuctions() {
        AuctionService.getAuctions().then((response) => {
            this.setState({auctions: response.data})
            this.setState({currentAuctions: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    signUp(user){
        AuthenticationService.singUp(user).then((response) => {
            localStorage.setItem("jwt_token", response.data)
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    signIn(userData){
        AuthenticationService.getToken(userData).then((response) => {
            localStorage.setItem("jwt_token", response.data)
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    signOut() {
        localStorage.clear()
    }
}

export default App;
