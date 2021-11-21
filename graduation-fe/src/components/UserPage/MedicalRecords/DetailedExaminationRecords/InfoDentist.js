import http from '../../../service/http-common'
import { useEffect, useState } from 'react'
import { formatDate } from "../../../../utils/moment-helper";
export default function InfoDentist() {

    //bookingId
    let id = Number(sessionStorage.getItem("bookingId"));

    // lấy thông tin bác sĩ
    const [dentist, setDentist] = useState({

        id: "",
        accounts: {
            email: "",
        },
        image: "",
        cccd: "",
        fullName: "",
        birthday: "",
        gender: false,
        exp: "",
        communes: {}


    });

    useEffect(() => {
        http({
            url: '/booking/' + id,
            method: 'GET'
        })
            .then((response) => {
                const { data } = response;
                setDentist(data.data.dentistProfile);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, [id])

    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";

    return (
        <div>
            <div className="row border border-dark mt-5 mb-5" style={{ minHeight: '260px' }}>
                <h3>Thông tin bác sĩ</h3>

                <div className="col-md-2 ms-5">
                    <div >
                        <label htmlFor="exampleFormControlInput1" className="form-label">Hình ảnh:</label><br />
                        <img id="exampleFormControlInput1" src={srcUrl + dentist.image} alt="..."
                            style={{ height: '10rem', width: '10rem' }}></img>
                    </div>
                </div>

                <div className="col-md-3 mb-3">
                    <div className="form-group mt-3">
                        <label htmlFor="name">Họ và tên</label>
                        <input name="name" className="form-control"
                            id="name" autoComplete="off"
                            type="text" readOnly value={dentist.fullName} />
                    </div>
                    <div className="form-group mt-3 ">
                        <label htmlFor="birthday">Ngày sinh</label>
                        <input name="birthday" className="form-control"
                            value={formatDate(dentist.birthday)}
                            id="birthday" autoComplete="off" type="text" readOnly />
                    </div>

                </div>
                <div className="col-md-3">
                    <div className="form-group mt-3">
                        <label htmlFor="emailns">Email</label>
                        <input name="emailns" className="form-control"
                            value={dentist.accounts.email}
                            id="emailns" autoComplete="off" type="email" readOnly />
                    </div>

                </div>

                <div className="mt-3 col-md-3">
                    <label htmlFor="exp">Kinh nghiệm</label>
                    <textarea name="exp" className="form-control"
                        value={dentist.exp}
                        id="exp" autoComplete="off" type="text" rows="4" readOnly />
                </div>



            </div>
        </div>
    )
}