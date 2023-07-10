import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import UpdateUser from "../update/UpdateUser";

function UserComponent(props) {

    const [showUser, setUserShow] = useState(false);

    const userHandleClose = () => setUserShow(false)
    const userHandleShow = () => setUserShow(true)

    return (
        <div className="item">
            <img src={"./img/user/" + props.user.photo} alt=""/>
            <h2>{props.user.name + " " + props.user.surname + " " + props.user.patronymic}</h2>

            <b><label className="auction_field"><b>Role: </b></label>{props.user.role}</b>

            <b><label className="auction_field"><b>Email: </b></label>{props.user.email}</b>

            <b><label className="auction_field"><b>Phone number: </b></label>{props.user.phoneNumber}</b>

            <b><label className="auction_field"><b>Country: </b></label>{props.user.registrationAddress.country}</b>

            <b><label className="auction_field"><b>City: </b></label>{props.user.registrationAddress.city}</b>

            <b><label className="auction_field"><b>Identity Document:
            </b></label> {props.user.identification.identityDocument}</b>

            <b><label className="auction_field"><b>Series and number:
            </b></label>{props.user.identification.seriesAndDocumentNumber}</b>
            <Button onClick={userHandleShow} className="auction_button" variant="primary" type="submit">
                Update
            </Button>
            <Button onClick={() => {props.delete(props.user.id)}} className="auction_button" variant="danger" type="submit">
                Delete
            </Button>
            <UpdateUser showModal={showUser} closeModal={userHandleClose} update={props.update} user={props.user}/>
        </div>
    );
}

export default UserComponent;