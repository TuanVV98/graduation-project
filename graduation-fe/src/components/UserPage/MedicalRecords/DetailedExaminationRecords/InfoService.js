import http from '../../../service/http-common'
import {useEffect,useState} from 'react'
export default function InfoService() {
   
    //bookingId
    let id=Number(sessionStorage.getItem("bookingId"));


    // lấy thông tin dịch vụ
    const [bookingDetail,setBookingDetail]=useState([{
        id: "",
        bookingId: "",
        serviceId: "",
        voucherId: "",
        price: ""
    }]);

    //CALL API BOOKING DETAIL 
    useEffect(()=>{
        http({
            url:'/booking/detail/all/'+id,
            method:'GET'
        })
            .then((response)=>{
                const {data}=response;
                setBookingDetail(data.data);
            })
            .catch((error)=>{
                console.log(error,error.response);
            });
    },[id])

    //CALL API SERVICE
    const[service,setService]=useState({});
       
    useEffect(()=>{
        if(bookingDetail[0].serviceId!==null && 
            typeof(bookingDetail[0].serviceId)!=='undefined'&& 
            bookingDetail[0].serviceId!==""){
            http({
                url:'/services/'+bookingDetail[0].serviceId,
                method:'GET'
            })
                .then((response)=>{
                    const {data}=response;
                    setService(data.data);
                    // console.log(data.data)
                })
                .catch((error)=>{
                    console.log(error,error.response);
                });

        }
        
    },[bookingDetail])


    //CALL API VOUCHER
    const[voucher,setVoucher]=useState({});
    useEffect(()=>{
        if(typeof (bookingDetail[0].voucherId) !== "undefined" && 
        bookingDetail[0].voucherId !== null && bookingDetail[0].voucherId!==""){
           
            http({
                url:'/vouchers/byId/'+bookingDetail[0].voucherId,
                method:'GET'
            })
                .then((response)=>{
                    const {data}=response;
                    setVoucher(data.data);
                })
                .catch((error)=>{
                    console.log(error,error.response);
                });

        }
    },[bookingDetail])

  
    return (
        
        <div className="mb-5">
           
            <h3>Thông tin dịch vụ</h3>
            <table className="table table-bordered border border-1 
                text-center table-hover">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Tên dịch vụ</th>
                        <th scope="col">Mã giảm giá</th>
                        <th scope="col">Giá tiền</th>
                    </tr>
                </thead>

                <tbody>
        
                     
                            <tr >
                                <td>{bookingDetail[0].id}</td>
                                <td>{service!=null?service.name:""}</td>
                                <td>{voucher!=null?voucher.id:""}</td>
                                <td>{bookingDetail[0].price}</td>
                             </tr>
                          
                
                   
                </tbody>
            </table>
        </div>
    );
}