import React from 'react';
import {Button, Col, Form, FormCheck, FormControl, FormGroup, FormLabel, Modal, Row} from "react-bootstrap";
import {Controller, useForm} from "react-hook-form";

function CreateBid(props) {

    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            suggestedPrice: "",
            auctionId: props.auction.id
        }
    });

    const onSubmit = data => {
        console.log("Bid data - " + data.auctionId + data.suggestedPrice)
        props.createBid(data.auctionId, data.suggestedPrice)
    };

    return (
        <div>
            <Modal centered size="lg" show={props.showModal} onHide={props.closeModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Participation in the auction</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit(onSubmit)}>
                        <Form.Group as={Row} className="mb-3" controlId="formPlaintextAuctionId">
                            <Form.Label column sm="2">
                                Auction Id
                            </Form.Label>
                            <Col sm="10">
                                <Form.Control plaintext readOnly defaultValue={props.auction.id}/>
                            </Col>
                        </Form.Group>
                        <FormGroup controlId="formBasicPrice">
                            <FormLabel>Suggested price</FormLabel>
                            <Controller
                                control={control}
                                name="suggestedPrice"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter suggested price"
                                            {...field}
                                            type={"text"}
                                            multiline={false}
                                            helperText={fieldState.error?.message || " "}
                                            error={!!fieldState.error}
                                        />
                                    )
                                }
                            ></Controller>
                        </FormGroup>
                        <FormGroup controlId="formBasicCheckBox">
                            <FormCheck type="checkbox"
                                       label="I agree with the terms and conditions of participation in the auction"/>
                        </FormGroup>
                        <Button className="auction_button" variant="primary" type="submit">
                            Submit
                        </Button>
                        <Button className="auction_button" variant="danger" type="cancel">
                            Cancel
                        </Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </div>
    );
}

export default CreateBid;