import TextField from '@material-ui/core/TextField';
import { makeStyles } from '@material-ui/core/styles';
export default function ShowDetailLichLamViec() {
    //css
    const useStyles = makeStyles((theme) => ({
        container: {
            display: 'flex',
            flexWrap: 'wrap',
        },
        textField: {
            marginLeft: theme.spacing(1),
            marginRight: theme.spacing(1),
            width: 300,
        },
        time:{
            width:100,
        }
    }));
    const classes = useStyles();
    //end css
    return (
        <div>
            <div className="row border border-dark">
                <h3>Chi tiết lịch làm việc</h3>
                <div className="col-md-4 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="id">Id</label>
                        <input name="id" className="form-control"
                            id="id" autoComplete="off" defaultValue="1" type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="date">Ngày đặt lịch</label>
                        <input name="date" className="form-control" defaultValue="07/10/2021"
                            id="date" autoComplete="off" type="text" readOnly />
                    </div>
                </div>


                <div className="col-md-4 mb-3">
                    {/* Thay đổi thời gian khám */}
                    <form>
                        <div className="form-group mt-3">
                            <TextField
                                label="Ngày khám"
                                id="dateK"
                                type="date"
                                name="dateK"
                                defaultValue="2021-05-24"
                                className={classes.textField}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                        </div>

                        <div className="col-sm-12 row">
                            <div className="form-group mt-3 col-sm-6">
                                <TextField
                                    label="Bắt đầu"
                                    name="start"
                                    id="start"
                                    type="time"
                                    className={classes.time}
                                    defaultValue="07:30"
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    inputProps={{
                                        step: 300, // 5 min
                                    }}
                                />
                            </div>
                            <div className="form-group mt-3 col-sm-6">
                                <TextField
                                    label="Kết thúc"
                                    name="end"
                                    id="end"
                                    type="time"
                                    className={classes.time}
                                    defaultValue="10:30"
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    inputProps={{
                                        step: 300, // 5 min
                                    }}
                                />
                            </div>
                        </div>
                        <button className="btn btn-info mt-3">Thay đổi</button>
                    </form>
                    {/* end thay đổi thời gian khám */}
                </div>


                <div className="form-group mt-3 col-md-4">
                    <label htmlFor="decription">Mô tả</label>
                    <textarea name="decription" className="form-control" defaultValue="nhổ răng"
                        id="decription" autoComplete="off" type="text" rows="4" readOnly />
                </div>
            </div>
        </div>
    )
}