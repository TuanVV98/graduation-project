import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import http from '../../../service/http-common'
import { useEffect, useState } from 'react'
//css
const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    backButton: {
        marginRight: theme.spacing(1),
    },
    instructions: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1),
    },
}));
//end css

//STATUS
function getSteps() {
    return ['Đang chờ xác nhận', 'Đã khám'];
}

//MÔ TẢ STATUS
function getStepContent(stepIndex) {
    switch (stepIndex) {
        case 0:
            return 'Vui lòng xác nhận lịch khám';
        case 1:
            return 'Xác nhận thành công! Bạn nhớ xem lịch và đến đúng giờ.';
        case 2:
            return 'Đã khám';
        default:
            return 'Hủy lịch khám';
    }
}

export default function StepByStep({ setUpdateList }) {



    //bookingId
    let id = Number(sessionStorage.getItem("bookingId"));

    // let status = query.get('status');
    const classes = useStyles();
    const steps = getSteps();
    const [activeStep, setActiveStep] = React.useState(0);






    // lấy thông tin đặt lịch
    const [booking, setBooking] = useState({
        id: "",
        dentistProfile: {},
        customerProfile: {
            id: "",
            accounts: {
                id: ""
            }
        },
        bookingDate: "",
        description: "",
        status: "",
        scheduleTime: {
            dayOfWeek: "",
            end: "",
            start: ""
        }


    });
    useEffect(() => {
        http({
            url: '/booking/' + id,
            method: 'GET'
        })
            .then((response) => {
                const { data } = response;
                setBooking(data.data);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, [id])


    useEffect(() => {
        setActiveStep(Number(booking.status))
    }, [booking.status])


    // // -đang chờ xác nhận(0)-chờ khám (1)
    // // -đã khám(hoàn thành(2)-huy(3)
    // //HỦY LỊCH
    const btnHuyLich = () => {
        const confirm = window.confirm("Bạn muốn hủy lịch khám này không ?");
        if (confirm === true) {
            setActiveStep(3);
            updateStatus(3)
            // setUpdateList(3);


        }
    };

    // cập nhật trạng thái đặt lịch 
    const handleNext = () => {
        const confirm = window.confirm("Bạn muốn cập nhật lịch khám này không ?");
        if (confirm === true) {
            setActiveStep((prevActiveStep) => prevActiveStep + 1);
            updateStatus(activeStep + 1);
            // setUpdateList(activeStep+1);

        }
    };

    //UPDATE STATUS
    const updateStatus = (status) => {
        console.log(status)
        if (
            booking.customerProfile.accounts !== null &&
            typeof (booking.customerProfile.accounts) !== 'undefined') {
            console.log(booking.customerProfile.accounts);
            http({
                url: '/booking/' + id + "/status/" + status,
                method: 'PUT'
            })
                .then((response) => {
                    console.log("UPDATE STATUS SUCCESS");
                     setUpdateList((prevActiveStep) => prevActiveStep + 1);

                })
                .catch((error) => {
                    console.log(error, error.response);
                });
        }
    }



    return (
        <div className={classes.root}>
            <h3>Trạng thái</h3>
            {
                activeStep !== 3 ? (
                    <Stepper activeStep={activeStep} alternativeLabel>
                        {steps.map((label) => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                ) : ''
            }

            <div>
                {activeStep === steps.length ? (
                    <div>
                        <Typography className={classes.instructions}>Đã hoàn thành dịch vụ !</Typography>
                    </div>
                ) : (
                    <div>
                        <Typography className={classes.instructions}>{getStepContent(activeStep)}</Typography>
                        <div className="mb-2">
                            {
                                activeStep !== 3 ? (
                                    <Button variant="contained" color="primary" onClick={handleNext}>
                                        {activeStep === steps.length - 1 ? 'Kết thúc' : 'Tiếp tục'}
                                    </Button>
                                ) : ""
                            }


                            {
                                activeStep === 0 ? (
                                    <Button variant="contained" color="secondary"
                                        className="ms-3"
                                        onClick={btnHuyLich}>
                                        Hủy lịch khám
                                    </Button>
                                ) : ''

                            }

                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}