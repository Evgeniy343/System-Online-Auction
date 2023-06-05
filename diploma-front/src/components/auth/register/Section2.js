import React, {Component} from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";

function Section2(props) {
        return (
            <Container>
                <Row>
                    <Col xs={6} md={4}>
                        <FormGroup controlId="formBasicCountry">
                            <FormLabel>Country</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.country"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter country"
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
                        <FormGroup controlId="formBasicRegion">
                            <FormLabel>Region</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.region"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter region"
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
                        <FormGroup controlId="formBasicCity">
                            <FormLabel>City</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.city"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter city"
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

                <Row>
                    <Col xs={6} md={4}>
                        <FormGroup controlId="formBasicStreet">
                            <FormLabel>Street</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.street"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter street"
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
                        <FormGroup controlId="formBasicHouse">
                            <FormLabel>House</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.house"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter house"
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
                        <FormGroup controlId="formBasicFlat">
                            <FormLabel>Flat</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.flat"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter flat"
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

export default Section2;