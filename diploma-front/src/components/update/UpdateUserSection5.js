import React from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";

function UpdateUserSection5(props) {
    return (
        <Container>
            <Row>
                <Col xs={6} md={6}>
                    <FormGroup controlId="formBasicCurrentAccountNumber">
                        <FormLabel>Account number</FormLabel>
                        <Controller
                            control={props.control}
                            name="requisitesForReturnOfDeposit.currentAccountNumber"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter account number"
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
                <Col xs={6} md={6}>
                    <FormGroup controlId="formBasicBankNameAndBranchNumber">
                        <FormLabel>Bank name and branch number</FormLabel>
                        <Controller
                            control={props.control}
                            name="requisitesForReturnOfDeposit.bankNameAndBranchNumber"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter bank name and branch number"
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
                <Col xs={6} md={6}>
                    <FormGroup controlId="formBasicBankCode">
                        <FormLabel>bank code</FormLabel>
                        <Controller
                            control={props.control}
                            name="requisitesForReturnOfDeposit.bankCode"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter bank code"
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
                <Col xs={6} md={6}>
                    <FormGroup controlId="formBasicBankAddress">
                        <FormLabel>Bank address</FormLabel>
                        <Controller
                            control={props.control}
                            name="requisitesForReturnOfDeposit.bankAddress"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter bank address"
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

export default UpdateUserSection5;