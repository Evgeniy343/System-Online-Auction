import React from 'react';
import {Controller, useForm} from "react-hook-form";
import {Button, Form, FormCheck, FormControl, FormGroup, FormLabel, FormText, Modal} from "react-bootstrap";
import Section1 from "../auth/register/Section1";
import Section2 from "../auth/register/Section2";
import Section3 from "../auth/register/Section3";
import Section4 from "../auth/register/Section4";
import Section5 from "../auth/register/Section5";
import SectionAuction1 from "./SectionAuction1";
import SectionAuction2 from "./SectionAuction2";
import SectionAuction3 from "./SectionAuction3";

function CreateAuction(props) {

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
        props.onCreateAuction(data)
    };

    return (
        <Modal size="lg" show={props.showModal} onHide={props.closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Create Auction</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit(onSubmit)}>

                    <SectionAuction1 control={control}/>
                    <SectionAuction2 control={control}/>
                    <SectionAuction3 control={control}/>

                    <Button variant="primary" type="register">
                        Create
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}

export default CreateAuction;