import React from 'react';
import {useForm} from "react-hook-form";
import {Button, Form, Modal} from "react-bootstrap";
import SectionAuction1 from "../create/SectionAuction1";
import SectionAuction2 from "../create/SectionAuction2";
import SectionAuction3 from "../create/SectionAuction3";
import UpdateAuctionSection1 from "./UpdateAuctionSection1";
import UpdateAuctionSection2 from "./UpdateAuctionSection2";
import UpdateAuctionSection3 from "./UpdateAuctionSection3";

function UpdateAuction(props) {

    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            initialRate: "",
            depositAmount: "20%",
            fullPaymentTerm: "",
            tradingStartTime: "",
            tradingCloseTime: "",
            product: {
                name: "",
                category: "",
                description: "",
                photos: [
                    {
                        name: ""
                    }
                ]
            },
            status: "ACTIVE"
        },
    });

    const onSubmit = data => {
        console.log("Auction data - " + data)
        props.update(data)
    };

    return (
        <Modal size="lg" show={props.showModal} onHide={props.closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Update Auction</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit(onSubmit)}>

                    <UpdateAuctionSection1 auction={props.auction} control={control}/>
                    <UpdateAuctionSection2 auction={props.auction} control={control}/>
                    <UpdateAuctionSection3 auction={props.auction} control={control}/>

                    <Button variant="primary" type="register">
                        Update
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}

export default UpdateAuction;