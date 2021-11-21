import http from '../../../service/http-common'
import {useEffect,useState} from 'react'
import { formatDate } from '../../../../utils/moment-helper';
export default function ShowDetailMedicalRecords() {
    
    //bookingId
    let id=Number(sessionStorage.getItem("bookingId"));

    // lấy thông tin đặt lịch
    const [booking,setBooking]=useState({
        id: "",
        dentistProfile: {},
        customerProfile: {},
        bookingDate: "",
        description: "",
        status: "",
        scheduleTime: {
            dayOfWeek:"",
            end:"",
            start:""
        }
           
        
    });
    useEffect(()=>{
        if(typeof(id)!==undefined&& id!==null){
            http({
                url:'/booking/'+id,
                method:'GET'
            })
                .then((response)=>{
                    const {data}=response;
                    setBooking(data.data);
                })
                .catch((error)=>{
                    console.log(error,error.response);
                });

        }
       
    },[id])
   
    
    return (
        <div>
            <div className="row border border-dark">
                <h3>Chi tiết hồ sơ khám</h3>
                <div className="col-md-4">
                    <div className="form-group mt-3">
                        <label htmlFor="id">Id</label>
                        <input name="id" className="form-control"
                            id="id" autoComplete="off" value={booking.id} 
                            type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="date">Ngày đặt lịch</label>
                        <input name="date" className="form-control"
                            id="date" autoComplete="off" value={formatDate(booking.bookingDate)}
                            type="text" readOnly />
                    </div>
                </div>

                <div className="col-md-4 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="dateK">Ngày khám</label>
                        <input name="dateK" className="form-control" 
                        value={formatDate(booking.scheduleTime.dayOfWeek) }
                            id="dateK" autoComplete="off" type="text" readOnly />
                    </div>
                    
                    <div className="col-sm-13 row">
                        <div className="form-group mt-3 col-sm-6">
                            <label htmlFor="start">Bắt đầu</label>
                            <input name="start" className="form-control" 
                            value={booking.scheduleTime.start!==null && typeof (booking.scheduleTime.start) !== 'undefined'?
                                booking.scheduleTime.start.replace("1970-01-01T",""):""}
                                id="start" autoComplete="off" type="text" readOnly />
                        </div>
                        <div className="form-group mt-3 col-sm-6">
                            <label htmlFor="end">Kết thúc</label>
                            <input name="end" className="form-control" 
                            value={booking.scheduleTime.end!==null && typeof (booking.scheduleTime.end) !== 'undefined'?
                            booking.scheduleTime.end.replace("1970-01-01T",""):""}
                                id="end" autoComplete="off" type="text" readOnly />
                        </div>
                    </div>

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