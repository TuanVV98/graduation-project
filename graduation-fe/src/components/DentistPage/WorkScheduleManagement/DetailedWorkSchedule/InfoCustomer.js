import http from '../../../service/http-common'
import { useEffect, useState } from 'react'
import { formatDate } from "../../../../utils/moment-helper";
export default function InfoCustomer() {

    //bookingId
    let id = Number(sessionStorage.getItem("bookingId"));

    // lấy thông tin khách hàng
    const [customer, setCustormer] = useState({

        id: "",
        accounts: {
            email: "",
        },
        image: "",
        fullname: "",
        birthday: "",
        gender: false,
        communes: {},
        telephone:"",
        story:""


    });

    useEffect(() => {
        http({
            url: '/booking/' + id,
            method: 'GET'
        })
            .then((response) => {
                const { data } = response;
                setCustormer(data.data.customerProfile);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, [id])

    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";

    return (
        <div>
            <div className="row border border-dark mt-5 mb-5" style={{ minHeight: '260px' }}>
                <h3>Thông tin khách hàng</h3>

                <div className="col-md-2 ms-5">
                    <div >
                        <label htmlFor="exampleFormControlInput1" className="form-label">Hình ảnh:</label><br />
                        <img id="exampleFormControlInput1"
                            src={srcUrl+customer.image}
                            className="card-img" alt="no img"
                            style={{ height: '150px', width: '180px' }} />
                    </div>
                </div>

                <div className="col-md-3 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="id">Id</label>
                        <input name="id" className="form-control"
                            id="id" autoComplete="off" value={customer.id}
                            type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="name">Họ và tên</label>
                        <input name="name" className="form-control"
                            id="name" autoComplete="off" value={customer.fullname}
                            type="text" readOnly />
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="form-group mt-3 ">
                        <label htmlFor="birthday">Ngày sinh</label>
                        <input name="birthday" className="form-control" value={formatDate(customer.birthday)}
                            id="birthday" autoComplete="off" type="text" readOnly />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="telephone">Điện thoại</label>
                        <input name="telephone" className="form-control"
                            value={customer.telephone}
                            id="telephone" autoComplete="off" type="text" readOnly />
                    </div>
                </div>

                <div className="mt-3 col-md-3">
                    <label htmlFor="exp">Mô tả</label>
                    <textarea name="exp" className="form-control"
                        value={customer.story}
                        id="exp" autoComplete="off" type="text" rows="4" readOnly />
                </div>



            </div>
        </div>
    )
}