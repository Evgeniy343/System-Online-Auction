import React from 'react';
import {useForm} from "react-hook-form";
import {Button, Col, Form, FormCheck, FormControl, FormGroup, FormLabel, Modal, Row} from "react-bootstrap";

function EditBid(props) {
    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            suggestedPrice: "",
            auctionId: ""
        }
    });

    const onSubmit = data => {

    };

    return (
        <div>
            <Modal centered size="lg" show={props.showModal} onHide={props.closeModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Participation in the auction</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Row} className="mb-3" controlId="formPlaintextAuctionId">
                            <Form.Label column sm="2">
                                Auction id
                            </Form.Label>
                            <Col sm="10">
                                <Form.Control plaintext readOnly defaultValue="3435346346"/>
                            </Col>
                        </Form.Group>
                        <FormGroup controlId="formBasicPrice">
                            <FormLabel>Proposed price</FormLabel>
                            <FormControl type="text" placeholder="Enter proposed price"/>
                        </FormGroup>
                        <FormGroup controlId="formBasicCheckBox">
                            <FormCheck type="checkbox"
                                       label="I agree with the terms and conditions of participation in the auction"/>
                        </FormGroup>
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                        <Button variant="danger" type="cancel">
                            Cancel
                        </Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </div>
    );
}

export default EditBid;
