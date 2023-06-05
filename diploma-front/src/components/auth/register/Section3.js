import React, {Component} from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";

function Section3(props) {
        return (
            <Container>
                <Row>
                    <Col xs={6} md={6}>
                        <FormGroup controlId="formBasicCopyOfDocumentPageWithPhoto"
                                   className="mb-3">
                            <FormLabel>Copy page with photo</FormLabel>
                            <Controller
                                control={props.control}
                                name="copiesOfDocuments.copyOfDocumentPageWithPhoto"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            {...field}
                                            type={"file"}
                                            multiline={false}
                                            helperText={fieldState.error?.message || " "}
                                            error={!!fieldState.error}
                                        />
                                    )
                                }
                            ></Controller>
                        </FormGroup>
                    </Col>
                    <Col xs={6} md={6}>
                        <FormGroup controlId="formBasicCopyOfDocumentPageWithPersonalNumber"
                                   className="mb-3">
                            <FormLabel>Copy personal number</FormLabel>
                            <Controller
                                control={props.control}
                                name="copiesOfDocuments.copyOfDocumentPageWithPersonalNumber"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            {...field}
                                            type={"file"}
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
                    <Col xs={6} md={6}>
                        <FormGroup controlId="formBasicCopyOfPageOfDocumentResidencePermit"
                                   className="mb-3">
                            <FormLabel>Copy residence permit</FormLabel>
                            <Controller
                                control={props.control}
                                name="copiesOfDocuments.copyOfPageOfDocumentResidencePermit"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            {...field}
                                            type={"file"}
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

export default Section3;