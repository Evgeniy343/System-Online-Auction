import React from 'react';
import {Col, Container, Form, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";

function UpdateAuctionSection3(props) {
    return (
        <Container>
            <Row>
                <Form.Group as={Col} controlId="formGridInitialRate">
                    <Form.Label>Initial rate</Form.Label>
                    <Controller
                        defaultValue={props.auction.initialRate}
                        control={props.control}
                        name="initialRate"
                        render={
                            ({field, fieldState}) => (
                                <FormControl
                                    {...field}
                                    type={"text"}
                                    multiline={false}
                                    helperText={fieldState.error?.message || " "}
                                    error={!!fieldState.error}
                                />
                            )
                        }
                    ></Controller>
                </Form.Group>
            </Row>
            <Row>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicFullPaymentTerm">
                        <FormLabel>Full payment term</FormLabel>
                        <Controller
                            defaultValue={props.auction.fullPaymentTerm}
                            control={props.control}
                            name="fullPaymentTerm"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
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
                </Col>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicTradingStartTime">
                        <FormLabel>Trading start time</FormLabel>
                        <Controller
                            defaultValue={props.auction.tradingStartTime}
                            control={props.control}
                            name="tradingStartTime"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
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
                </Col>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicTradingCloseTime">
                        <FormLabel>Trading close time</FormLabel>
                        <Controller
                            defaultValue={props.auction.tradingCloseTime}
                            control={props.control}
                            name="tradingCloseTime"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
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
                </Col>
            </Row>
        </Container>
    );
}

export default UpdateAuctionSection3;