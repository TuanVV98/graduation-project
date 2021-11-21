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
            return 'Vui lòng chờ xác nhận của nha sĩ.';
        case 1:
            return 'Xác nhận thành công! Bạn nhớ xem lịch và đến khám đúng giờ.';
        case 2:
            return 'Đã khám';
        default:
            return 'Hủy lịch khám';
    }
}

export default function StepByStep({ setUpdateStatus }) {

    //bookingId
    let id = Number(sessionStorage.getItem("bookingId"));

    // let status = query.get('status');
    const classes = useStyles();
    const steps = getSteps();
    const [activeStep, setActiveStep] = React.useState(0);


    //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
    const [auth, setAuth] = useState({});
    useEffect(() => {
        let json = localStorage.getItem("auth");
        if (json != null && typeof (json) !== 'undefined') {
            setAuth(JSON.parse(json));
        }
    }, [])




    // lấy thông tin đặt lịch
    const [booking, setBooking] = useState({
        id: "",
        dentistProfile: {},
        customerProfile: {},
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


    // -đang chờ xác nhận(0)-chờ khám (1)
    // -đã khám(hoàn thành(2)-huy(3)
    //HỦY LỊCH
    const btnHuyLich = () => {
        const confirm = window.confirm("Bạn muốn hủy lịch khám này không ?");
        if (confirm === true) {
            setActiveStep(3);

            if(auth.id!==null && typeof(auth.id)!=='undefined'){
                http({
                    url: '/booking/' + id + "/status/3/",
                    method: 'PUT'
                })
                    .then((response) => {
                        setUpdateStatus(true);
                        console.log("UPDATE STATUS SUCCESS");
                    })
                    .catch((error) => {
                        console.log(error, error.response);
                    });
            }
            
        }
    };


    // //TEST
    // const handleNext = () => {
    //     setActiveStep((prevActiveStep) => prevActiveStep + 1);
    //   };

    return (
        <div className={classes.root}>

            <h3>Trạng thái</h3>
            {activeStep !== 3 ? (
                <Stepper activeStep={activeStep} alternativeLabel>
                    {steps.map((label) => (
                        <Step key={label}>
                            <StepLabel>{label}</StepLabel>
                        </Step>
                    ))}
                </Stepper>
            ) : ''}

            <div>
                {activeStep === steps.length ? (
                    <div>
                        <Typography className={classes.instructions}>Cảm ơn đã sử dụng dịch vụ !</Typography>
                    </div>
                ) : (
                    <div>
                        <Typography className={classes.instructions}>{getStepContent(activeStep)}</Typography>
                        <div>

                            {/* <Button variant="contained" color="primary" onClick={handleNext}>
                                {activeStep === steps.length - 1 ? 'Finish' : 'Next'}
                            </Button> */}

                            {
                                activeStep === 0 ? (
                                    <Button variant="contained" color="secondary"
                                        className="mb-3"
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