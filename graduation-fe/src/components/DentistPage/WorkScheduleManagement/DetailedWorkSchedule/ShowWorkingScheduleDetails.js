import http from '../../../service/http-common'
import { useEffect, useState } from 'react'
import { formatDate } from '../../../../utils/moment-helper';
import { useForm } from "react-hook-form";
import moment from "moment";
export default function ShowWorkingScheduleDetails({ updateList }) {


    //bookingId
    let id = Number(sessionStorage.getItem("bookingId"));

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
        },
        deleteAt: ""


    });

    useEffect(() => {
        if (typeof (id) !== 'undefined' && id !== null) {
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

        }

    }, [id, updateList])


    //CẬP NHẬT THỜI GIAN
    const {
        register,
        handleSubmit,
    } = useForm();



    //Tìm ngày theo bác sĩ
    const [scheduleTime, setScheduleTime] = useState([]);
    useEffect(() => {
        if (booking.dentistProfile.id !== null && typeof (booking.dentistProfile.id) !== 'undefined')
            http({
                url: `http://localhost:8080/api/v1/schedule-time/dentist?dentistProfileId=${booking.dentistProfile.id}`,
                method: "GET",
            })
                .then((response) => {
                    const { data } = response;
                    setScheduleTime(data.data)
                })
                .catch((error) => {
                    console.log(error.response);
                });
    }, [booking]);


    const [hourScheduleTime, setHourScheduleTime] = useState([]);
    const [day, setDay] = useState(booking.scheduleTime.dayOfWeek);

    //set dayOfWeek
    const onChangeDayOfWeek = (e) => {
        setDay(e.target.value)
        document.getElementById('scheduleTime').value = ""
    };



    //Tìm giờ theo ngày
    // console.log(booking)
    useEffect(() => {
        if (booking.dentistProfile.id != null && booking.scheduleTime.dayOfWeek != null) {

            http({
                url: `http://localhost:8080/api/v1/schedule-time/hour?dayOfWeek=${booking.scheduleTime.dayOfWeek}&dentistId=${booking.dentistProfile.id}`,
                method: "GET",
            })
                .then((response) => {
                    const { data } = response;
                    setHourScheduleTime(data.data);
                })
                .catch((error) => {
                    console.log(error.response);
                });

        }



    }, [booking, day]);






    const onHandleSubmit = (data) => {
        const confirm = window.confirm("Bạn muốn cập nhật lịch khám này không ?");
        if (confirm === true) {
            onUpdateBooking();
        }
    };

    //PUT DATA
    const onUpdateBooking = () => {
        console.log(booking);
        http({
            url: `http://localhost:8080/api/v1/booking/` + booking.id,
            method: "PUT",
            data: booking,
        })
            .then((response) => {
                const { data } = response.data;
                setBooking({
                    ...booking,
                    data
                })
                alert('Thay đổi lịch thành công !')
            })
            .catch((error) => {
                console.log(error);
                const { data } = error.response
                alert("Cập nhật thất bại");
                data.errors.details !== null &&
                typeof(data.errors.details)!=='undefined' 
                ? alert(data.errors.details) : console.log("update fail");
            });
    };


    const bookingScheduleTime = register("scheduleTime", { required: true });
    const bookingDayOfWeek = register("dayOfWeek", { required: true });


    // console.log(updateList)

    return (
        <div>
            <div className="row border border-dark">
                <h3>Chi tiết lịch làm việc</h3>
                <div className="col-md-4 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="idB">Id</label>
                        <input name="idB" className="form-control"
                            id="idB" autoComplete="off" value={booking.id}
                            type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="date">Ngày đặt lịch</label>
                        <input name="date" className="form-control"
                            value={formatDate(booking.bookingDate)}
                            id="date" autoComplete="off" type="text" readOnly />
                    </div>
                </div>


                <div className="col-md-4 mb-3 row">
                    {/* Thay đổi thời gian khám */}
                    <form onSubmit={handleSubmit(onHandleSubmit)}>
                        <div className="col-md-12 mt-2">
                            <label htmlFor="bookingDate" className="form-label mt-1">
                                Chọn ngày:
                            </label>
                            <br />
                            <select
                                className="form-select"
                                aria-label="Default select example"
                                id="dayOfWeek"
                                name="dayOfWeek"
                                value={booking.scheduleTime.dayOfWeek}
                                {...bookingDayOfWeek}
                                disabled={booking.status === 2 || booking.status === 3
                                    ? true : false}
                                onChange={(e) => {
                                    bookingDayOfWeek.onChange(e);
                                    onChangeDayOfWeek(e);
                                    // onChangeHandler(e);
                                    setBooking({
                                        ...booking,
                                        scheduleTime: {
                                            // id:scheduleTimeId,
                                            dayOfWeek: e.target.value,
                                        },
                                    });
                                }}
                            >
                                {scheduleTime.map((schedule, idx) => {
                                    return (
                                        <option key={idx} value={schedule.dayOfWeek}>
                                            {moment(schedule.dayOfWeek).format("DD-MM-YYYY")}
                                        </option>
                                    );
                                })}
                            </select>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="scheduleTime" className="form-label">
                                Thời gian:
                            </label>
                            <select
                                className="form-select"
                                aria-label="Default select example"
                                id="scheduleTime"
                                name="scheduleTIme"
                                value={booking.scheduleTime.id}
                                {...bookingScheduleTime}
                                disabled={booking.status === 2 || booking.status === 3
                                    ? true : false}
                                onChange={(e) => {
                                    bookingScheduleTime.onChange(e);
                                    // onChangeHandler(e);
                                    // onChangeHour(e);
                                    setBooking({
                                        ...booking,
                                        scheduleTime: {
                                            id: e.target.value,
                                            dayOfWeek: day
                                        },
                                    });
                                }}
                            >
                                <option value=""> -- Chọn thời gian -- </option>
                                {hourScheduleTime.map((schedule, idx) => {
                                    return (
                                        <option key={idx} value={schedule.id}>
                                            {moment(schedule.start).format("HH:mm:ss")} -
                                            {moment(schedule.end).format("HH:mm:ss")}
                                        </option>
                                    );
                                })}
                            </select>
                        </div>


                        {
                            booking.status === 2 || booking.status === 3 ? "" : (
                                <button className="btn btn-info mt-3" >
                                    Thay đổi
                                </button>
                            )
                        }

                    </form>
                    {/* end thay đổi thời gian khám */}
                </div>


                <div className="form-group mt-3 col-md-4">
                    <label htmlFor="decription">Mô tả</label>
                    <textarea name="decription" className="form-control"
                        value={booking.description}
                        id="decription" autoComplete="off" type="text" rows="4" readOnly />
                </div>
            </div>
        </div>
    )
}