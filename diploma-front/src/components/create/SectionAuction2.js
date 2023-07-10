import React from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";
import Select from "react-select";

function SectionAuction2(props) {
    return (
        <Container>
            <Row>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formProductPhoto1">
                        <FormLabel>Photo 1</FormLabel>
                        <Controller
                            control={props.control}
                            name="product.photos[0].name"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter product name"
                                        {...field}
                                        type={"file"}
                                        label='product.photos[0].name'
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
                    <FormGroup controlId="formProductPhoto2">
                        <FormLabel>Photo 2</FormLabel>
                        <Controller
                            control={props.control}
                            name="product.photos[1].name"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        {...field}
                                        type={"file"}
                                        label='product.photos[1].name'
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
                    <FormGroup controlId="formProductPhoto3">
                        <FormLabel>Photo 3</FormLabel>
                        <Controller
                            control={props.control}
                            name="product.photos[2].name"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        {...field}
                                        type={"file"}
                                        label='product.photos[2].name'
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

export default SectionAuction2;