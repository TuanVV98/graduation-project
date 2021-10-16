import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

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
    return ['Đang chờ', 'Xác nhận', 'Đã khám'];
}

//MÔ TẢ STATUS
function getStepContent(stepIndex) {
    switch (stepIndex) {
        case 0:
            return 'Vui lòng chờ xác nhận của nha sĩ.';
        case 1:
            return 'Xác nhận thành công.';
        case 2:
            return 'Bạn nhớ xem lịch và đến khám đúng giờ.';
        default:
            return 'Smile Dental';
    }
}

export default function StepByStep() {

    const classes = useStyles();
    const [activeStep, setActiveStep] = React.useState(0);
    const steps = getSteps();

    const btnHuyLich = () => {
        const confirm = window.confirm("Bạn muốn hủy lịch khám này không ?");
        if (confirm === true) {
            //set status
        }
    };

    // TEST STEPPER
    // const handleNext = () => {
    //     setActiveStep((prevActiveStep) => prevActiveStep + 1);
    //   };



    return (
        <div className={classes.root}>
            <h3>Trạng thái</h3>
            <Stepper activeStep={activeStep} alternativeLabel>
                {steps.map((label) => (
                    <Step key={label}>
                        <StepLabel>{label}</StepLabel>
                    </Step>
                ))}
            </Stepper>
            <div>
                {activeStep === steps.length ? (
                    <div>
                        <Typography className={classes.instructions}>Cảm ơn đã sử dụng dịch vụ !</Typography>
                    </div>
                ) : (
                    <div>
                        <Typography className={classes.instructions}>{getStepContent(activeStep)}</Typography>
                        <div>
                            {/* Test Stepper */}
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