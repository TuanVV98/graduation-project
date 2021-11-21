import ImgHeaderRegister from "./ImgHeaderRegister";
import React from 'react';
import axios from 'axios';
import { useForm} from "react-hook-form";

export default function Register() {

    const { register, handleSubmit, formState: { errors }, watch } = useForm(
    );



    const onHandleSubmit = (data) => {
        // console.log(data);
        let setFormRegister = {
            email: data.email,
            password: data.cpPassword,
            telephone: data.telephone,
            rolesId: "CUSTOMER"
        }
        // console.log(setFormRegister);
        SubmitRegister(setFormRegister);
    }

    const SubmitRegister = (data) => {
        axios({
            url: `http://localhost:8080/api/v1/accounts/registerUser`,
            method: 'POST',
            data: data,
        })
            .then((response) => {
                alert("Tạo thành công, vui lòng kiểm tra mail để xác thực tài khoản");
            })
            .catch((error) => {
                console.log(error);
                
                // const { data } = error.response;
                // console.log(data);
                error.response.data.errors.details!==null &&
                typeof(error.response.data.errors.details)!=='undefined'?
                alert(error.response.data.errors.details + ". Vui lòng nhập lại")
                :console.log("ERROR NO MESSAGES");;
            });
    }

    const formEmail = register("email", {
        required: "Nhập email",
        pattern: {
            value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i, message: "Nhập đúng định dạng email"
        },
    });

    const formTelephone = register("telephone", {
        required: "Nhập số điện thoại",
        pattern: { value: /((0)+([0-9]{9})\b)/g, message: "Nhập số điện thoại đúng định dạng" },
    });

    const formPassword = register("password", {
        required: "Nhập mật khẩu",
        minLength: {
            value: 8,
            message: "Mật khẩu phải có 8 chữ số"
        }
    });

    const formcpPassword = register("cpPassword", {
        required: "Nhập lại mật khẩu",
        validate: value =>
            value === watch("password", "") || "Mật khẩu mới không đúng"
    });

    return (
        <div>
            <div>
                <ImgHeaderRegister />
            </div>

            <div className="container">
                <div className="col-md-12 row mt-5 d-flex justify-content-center" >
                    <div className="col-md-4" style={{ backgroundColor: 'rgb(246, 249, 249)' }}>
                        <form className="mx-2" onSubmit={handleSubmit(onHandleSubmit)}>

                            <div className="form-group mt-3">
                                <label htmlFor="email">Email</label>
                                <input name="email" className="form-control"
                                    id="email" autoComplete="off" type="email"
                                    {...formEmail}
                                    onChange={(e) => {
                                        formEmail.onChange(e);

                                    }}
                                /><div>{errors.email && <span className="text-danger">{errors.email.message}</span>}</div>
                            </div>

                            <div className="form-group mt-3">
                                <label htmlFor="telephone">Số điện thoại</label>
                                <input name="telephone" className="form-control"
                                    id="telephone" autoComplete="off" type="text"
                                    {...formTelephone}
                                    onChange={(e) => {
                                        formTelephone.onChange(e);

                                    }}
                                /><div>{errors.telephone && <span className="text-danger">{errors.telephone.message}</span>}</div>
                            </div>

                            <div className="form-group mt-3">
                                <label htmlFor="password">Mật khẩu</label>
                                <input name="password" className="form-control"
                                    id="password" autoComplete="off" type="password"
                                    {...formPassword}
                                    onChange={(e) => {
                                        formPassword.onChange(e);

                                    }}

                                /><div>{errors.password && <span className="text-danger">{errors.password.message}</span>}</div>
                            </div>

                            <div className="form-group mt-3">
                                <label htmlFor="cpPassword">Nhập lại mật khẩu</label>
                                <input name="cpPassword" className="form-control"
                                    id="cpPassword" autoComplete="off" type="password"
                                    {...formcpPassword}
                                    onChange={(e) => {
                                        formcpPassword.onChange(e);

                                    }}
                                /><div>{errors.cpPassword && <span className="text-danger">{errors.cpPassword.message}</span>}</div>
                            </div>

                            <div className="d-flex justify-content-center">
                                <button className="btn btn-info mt-3 mb-2" type="submit">Đăng ký</button>
                                <button className="btn btn-danger mt-3 mb-2 ms-3" type="reset">Hủy</button>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
    )
}