import React from "react";
import "bootstrap/dist/css/bootstrap.min.css"
import Footer from "./components/Footer";
import AuthenticationService from "./service/AuthenticationService";
import BasketService from "./service/BasketService";
import AuctionService from "./service/AuctionService";
import BidService from "./service/BidService";
import CardService from "./service/CardService";
import UserService from "./service/UserService";
import Header from "./components/Header";
import ListAuctionComponent from "./components/list/ListAuctionComponent";
import Categories from "./components/Categories";
import ShowFullAuctionInfo from "./components/info/ShowFullAuctionInfo";
import {Route, Routes, useNavigate} from "react-router-dom";
import ListMyAuctionsComponent from "./components/list/my/ListMyAuctionsComponent";
import ListUserComponent from "./components/list/ListUserComponent";
import ListMyBidsComponent from "./components/list/my/ListMyBidsComponent";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            authenticationData: {},
            users: [],
            myBids: [],
            myCards: [],
            myAuctions: [],
            bids: [],
            auctionsInBasket: [],
            auctions: [],
            currentAuctions: [],
            showFullAuctionInfo: false,
            fullAuctionInfo: {},
            showAuctions: false,
            showMyAuctions: false,
            showPay: false,
            showUsers: false,
            showBids: false,
            showCards: false,
            showSignIn: false,
            showSignUp: false,
            showSignOut: false,
            showBasket: false,
            showAddToBasket: false,
            showCreateBid: false,
            showCreateAuction: false,
            showCreateCard: false,
            showCreateUser: false
        }

        this.onShowFullAuctionInfo = this.onShowFullAuctionInfo.bind(this)
        this.chooseCategory = this.chooseCategory.bind(this)
        this.getAllAuctions = this.getAllAuctions.bind(this)

        this.addToBasket = this.addToBasket.bind(this)
        this.deleteAuctionFromBasket = this.deleteAuctionFromBasket.bind(this)
        this.getBasket = this.getBasket.bind(this)

        this.signOut = this.signOut.bind(this)
        this.signUp = this.signUp.bind(this)
        this.signIn = this.signIn.bind(this)

        this.getMyAuctions = this.getMyAuctions.bind(this)
        this.addAuction = this.addAuction.bind(this)
        this.deleteAuction = this.deleteAuction.bind(this)
        this.editAuction = this.editAuction.bind(this)

        this.getAllBids = this.getAllBids.bind(this)
        this.getMyBids = this.getMyBids.bind(this)
        this.addBid = this.addBid.bind(this)
        this.deleteBid = this.deleteBid.bind(this)
        this.editBid = this.editBid.bind(this)

        this.getCards = this.getCards.bind(this)
        this.getCardById = this.getCardById.bind(this)
        this.addCard = this.addCard.bind(this)
        this.deleteCard = this.deleteCard.bind(this)
        this.editCard = this.editCard.bind(this)

        this.getUserById = this.getUserById.bind(this)
        this.getAllUsers = this.getAllUsers.bind(this)
        this.addUser = this.addUser.bind(this)
        this.deleteUser = this.deleteUser.bind(this)
        this.editUser = this.editUser.bind(this)
        this.getAccount = this.getAccount.bind(this)

        this.getAccount = this.getAccount.bind(this)
    }

    render() {
        return (
            <div className="wrapper">
                <Header showSignIn={this.state.showSignIn} showSignUp={this.state.showSignUp}
                        showSignOut={this.state.showSignOut} showBids={this.state.showBids}
                        showCards={this.state.showCards} showUsers={this.state.showUsers}
                        showbasket={this.state.showBasket} showMyAuctions={this.state.showMyAuctions}
                        auctionsInBasket={this.state.auctionsInBasket}
                        deleteAuctionFromBasket={this.deleteAuctionFromBasket} signOut={this.signOut}
                        onSignIn={this.signIn} onSignUp={this.signUp} navigate={this.props.navigate}
                        getMyAuctions={this.getMyAuctions} getAllUsers={this.getAllUsers}
                        showCreateCard={this.state.showCreateCard} showCreateBid={this.state.showCreateBid}
                        showCreateAuction={this.state.showCreateAuction} showCreateUser={this.state.showCreateUser}
                        onShowFullAuctionInfo={this.onShowFullAuctionInfo} createUser={this.addUser}
                        createCard={this.addCard} createAuction={this.addAuction} getMyBids={this.getMyBids}/>

                {!this.state.showAuctions && <Categories chooseCategory={this.chooseCategory}/>}

                <Routes>
                    <Route exact path="/auctions"
                           element={<ListMyAuctionsComponent auctions={this.state.myAuctions}
                                                             onShowFullAuctionInfo={this.onShowFullAuctionInfo}
                                                             delete={this.deleteAuction} update={this.editAuction}/>}>
                    </Route>
                    <Route exact path="/users"
                           element={<ListUserComponent users={this.state.users} delete={this.deleteUser}
                                                       update={this.editUser}/>}>
                    </Route>
                    <Route exact path="/bids"
                           element={<ListMyBidsComponent bids={this.state.myBids}
                                                         onShowFullAuctionInfo={this.onShowFullAuctionInfo}
                                                         delete={this.deleteBid} update={this.editBid}/>}>
                    </Route>
                </Routes>
                {!this.state.showAuctions && <ListAuctionComponent onShowFullAuctionInfo={this.onShowFullAuctionInfo}
                                                                   auctions={this.state.currentAuctions}
                                                                   addToBasket={this.addToBasket}
                                                                   showAddToBasket={this.state.showAddToBasket}
                                                                   createBid={this.addBid}
                                                                   showPay={this.state.showPay}/>}
                {this.state.showFullAuctionInfo && <ShowFullAuctionInfo
                    addToBasket={this.addToBasket} onShowFullAuctionInfo={this.onShowFullAuctionInfo}
                    fullAuctionInfo={this.state.fullAuctionInfo}/>}
                <Footer/>
            </div>
        )
    }

    componentDidMount() {
        const token = localStorage.getItem("jwt_token")
        console.log("TOKEN - " + token)
        console.log(typeof token)
        console.log(typeof null)
        if (!!token && token.trim() !== 'undefined') {
            // true
            console.log("token is not null")
            AuthenticationService.refreshToken().then((response) => {
                this.setState({authenticationData: response.data})
                localStorage.clear()
                localStorage.setItem("jwt_token", response.data.token)
                console.log("ROLE - " + response.data.role)
                console.log("NEW TOKEN - " + localStorage.getItem("jwt_token"))
                if (response.data.role === "USER") {
                    this.getBasket()
                    this.setState({showBasket: true})
                    this.setState({showMyAuctions: true})
                    this.setState({showCards: true})
                    this.setState({showBids: true})
                    this.setState({showSignOut: true})
                    this.setState({showAddToBasket: true})
                    this.setState({showCreateBid: true})
                    this.setState({showCreateAuction: true})
                    this.setState({showCreateCard: true})
                    this.setState({showPay: true})
                }
                if (response.data.role === "ADMIN") {
                    this.setState({showCreateUser: true})
                    this.setState({showUsers: true})
                    this.setState({showSignOut: true})
                }
            }).catch(error => {
                console.log(error)
            })
        } else {
            // false
            console.log("token is null")
            this.setState({showSignIn: true})
            this.setState({showSignUp: true})
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


    getAllAuctions() {
        AuctionService.getAuctions().then((response) => {
            this.setState({auctions: response.data})
            this.setState({currentAuctions: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }


    // Basket
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    deleteAuctionFromBasket(auctionId) {
        BasketService.deleteAuctionFromBasket(auctionId).then((response) => {
            console.log(response.data)
            this.getBasket()
        }).catch(error => {
            console.log(error)
        })
    }

    getBasket() {
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
            this.getBasket()
        }).catch(error => {
            console.log(error)
        })
    }

    // Auction
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    getMyAuctions() {
        AuctionService.getMyAuctions().then((response) => {
            this.setState({myAuctions: response.data})
            console.log("My auctions - " + this.state.myAuctions)
            this.setState({showAuctions: true})
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/auctions")
    }

    deleteAuction(id) {
        AuctionService.deleteAuctionById(id).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    addAuction(auction) {
        AuctionService.saveAuction(auction).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    editAuction(id, auction) {
        AuctionService.updateAuctionById(id, auction).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    findAuction(name) {

    }

    // Bids
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    getAllBids() {
        BidService.getBids().then((response) => {
            this.setState({bids: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    getMyBids() {
        BidService.getUserBids().then((response) => {
            this.setState({myBids: response.data})
            this.setState({showAuctions: true})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/bids")
    }

    deleteBid(id) {
        BidService.deleteBidById(id).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    addBid(auctionId, suggestedPrice) {
        BidService.saveBid(auctionId, suggestedPrice).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    editBid(auctionId, suggestedPrice, bidId) {
        BidService.editBidById(auctionId, suggestedPrice, bidId).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    // Cards
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    getCards() {
        CardService.getCards().then((response) => {
            this.setState({myCards: response.data})
            this.setState({showAuctions: true})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    getCardById(id) {
        CardService.getCardById(id).then((response) => {
            this.setState({myBids: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    deleteCard(id) {
        CardService.deleteCardById(id).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    addCard(card) {
        CardService.saveCard(card).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    editCard(cardId, card) {
        CardService.editCardById(cardId, card).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }


    // Users
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    getAllUsers() {
        UserService.getUsers().then((response) => {
            this.setState({users: response.data})
            this.setState({showAuctions: true})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/users")
    }

    getUserById(id) {
        UserService.getUserById(id).then((response) => {
            this.setState({myBids: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    getAccount() {
        UserService.getAccount().then((response) => {
            this.setState({myBids: response.data})
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    deleteUser(id) {
        UserService.deleteUserById(id).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    addUser(user) {
        UserService.saveUser(user).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }

    editUser(userId, user) {
        UserService.editUserById(userId, user).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        this.props.navigate("/")
        window.location.reload()
    }


    // Authentication
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    signOut() {
        localStorage.clear()
        this.setState({showBasket: false})
        this.setState({showCards: false})
        this.setState({showMyAuctions: false})
        this.setState({showBids: false})
        this.setState({showUsers: false})
        this.setState({showSignOut: false})
        this.setState({showAddToBasket: false})
        this.setState({showCreateBid: false})
        this.setState({showCreateAuction: false})
        this.setState({showCreateCard: false})
        this.setState({showPay: false})
        this.setState({showCreateUser: false})
        this.setState({showSignIn: true})
        this.setState({showSignUp: true})
        this.props.navigate("/")
        window.location.reload()
    }


    signUp(user) {
        AuthenticationService.singUp(user).then((response) => {
            localStorage.setItem("jwt_token", response.data.token)
            console.log("role - " + response.data.role)
            console.log("token - " + response.data.token)
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        setTimeout(function () {
            window.location.reload();
        }, 2000);
    }

    signIn(userData) {
        AuthenticationService.getToken(userData).then((response) => {
            localStorage.setItem("jwt_token", response.data.token)
            console.log("role - " + response.data.role)
            console.log("token - " + response.data.token)
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
        setTimeout(function () {
            window.location.reload();
        }, 2000);
    }
}


export function AppWithRouter(props) {
    const navigate = useNavigate()

    return (
        <App navigate={navigate}/>
    )
}

export default App;
