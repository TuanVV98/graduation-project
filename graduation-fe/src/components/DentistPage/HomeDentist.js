import Typography from '@material-ui/core/Typography';
import {
    Switch,
    Route,
    Link,
    BrowserRouter
} from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import http from '../service/http-common';
import { useForm } from "react-hook-form";
import axios from 'axios';

import NavDentist from './NavDentist'
import DentistInformation from './DentistInformation/DentistInformation';
import Statistical from './Statistical/Statistical';
import WorkSchedule from './WorkScheduleManagement/WorkSchedule';
import Login from './Login/Login';



export default function HomeDentist() {

    const [isLogin, setIsLogin] = useState(localStorage.getItem("auth") !== null ? true : false);

    //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
    const [auth, setAuth] = useState({});
    useEffect(() => {
        let json = localStorage.getItem("auth");
        if (json != null && typeof (json) !== 'undefined') {
            setAuth(JSON.parse(json));
        }
    }, [isLogin])


    //
    //LẤY THÔNG TIN CHI TIẾT NGƯỜI DÙNG KHI CÓ ACCOUNT_ID
    const [changePW, setchangePW] = useState({
        id: "",
        email: "",
        password: "",
        telephone: "",
        updateAt: "",
        rolesId: "",
        deleteAt: false
    });
    const [account, setAccount] = useState({
        id: "",
        email: "",
        password: "",
        telephone: "",
        updateAt: "",
        rolesId: "",
        deleteAt: false
    });
    useEffect(() => {
        if (typeof (auth.id) !== 'undefined' && auth.id != null) {
            // console.log(auth.id)
            http({
                url: '/accounts/' + auth.id,
                method: 'GET',
            })
                .then((response) => {
                    const { data } = response;
                    setAccount(data.data);
                    setchangePW(data.data);
                })
                .catch((error) => {
                    console.log(error, error.response);
                });
        }

    }, [auth]);




    //LOGOUT
    const btnLogout = () => {
        setAccount({
            id: "",
            email: "",
            password: "",
            telephone: "",
            updateAt: "",
            rolesId: "",
            deleteAt: false
        });
        setIsLogin(false);
        localStorage.clear();
        sessionStorage.clear();

    }


    let token = localStorage.getItem("token");
    //
    //doi mat khau

    const { register, handleSubmit, formState: { errors }, watch } = useForm(
    );

    const onChangePW = (data) => {
        console.log("change PW");
        const yourDate = new Date()
        changePW.updateAt = yourDate.toISOString().split('T')[0];
        changePW.password = data.cpNewPassword;
        console.log(changePW);

        if (changePW.id !== "") {
            http({
                url: `http://localhost:8080/api/v1/accounts/chagepassword/${changePW.id}`,
                method: 'PUT',
                data: changePW,
            })
                .then((response) => {
                    alert("Đổi mật khẩu thành công");
                    window.location.reload();
                })
                .catch((error) => {
                    console.log(error.response);
                });
        }
    }

    const onHandleSubmit = (data) => {
        data.email = account.email;
        // console.log(data);
        const url = "http://localhost:8080/api/v1/user/auth"
        axios({
            url: url,
            method: 'POST',
            data: data,
        })
            .then((response) => {
                onChangePW(data);
            })
            .catch((error) => {
                alert("Sai mật khẩu");
            })
    }

    // const formPWEmail = register("email", { required: true });
    const formPWpassword = register("password", { required: true });
    const formPWnewPassword = register("newPassword", {
        required: "Nhập mật khẩu mới",
        minLength: {
            value: 8,
            message: "Mật khẩu phải có 8 chữ số"
        }
    });
    const formPWcpNewPassword = register("cpNewPassword", {
        required: "Nhập lại mật khẩu mới",
        validate: value =>
            value === watch("newPassword", "") || "Mật khẩu mới không đúng"
    });


    return (
        <BrowserRouter>
            <div className="container-fluid" >

                <div className="row min-vh-100 flex-column flex-md-row">

                    {/* sidebar */}
                    <aside className="col-12 col-md-3 col-xl-2 p-0 bg-dark flex-shrink-1">
                        {token != null ? (<NavDentist
                            setIsLogin={setIsLogin}
                            setAccount={setAccount} />) : ""}
                    </aside>
                    {/* sidebar */}

                    {/* container */}
                    <main className="col-12 col-md-9 px-0 flex-grow-1">
                        <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{ boxShadow: '1px 3px #f1f1f1' }}>
                            <div className="container-fluid">
                                <div className="collapse navbar-collapse " id="navbarSupportedContent">
                                    <ul className="navbar-nav me-auto mb-2 mb-lg-0 "></ul>
                                    <form className="d-flex">
                                        <div className="navbar-nav">
                                            <li className="nav-item dropdown">
                                                <span className="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <b>{token !== null && account.email !== "" && typeof (account.email) != 'undefined' ?
                                                        "Xin chào " + account.email.substring(0, account.email.indexOf("@")) : 'Xin chào, vui lòng đăng nhập'}</b>
                                                    <img src="https://f17-zpg.zdn.vn/3614250697572501720/429b3cec6084a9daf095.jpg" alt="logo" width="30" height="24" />
                                                </span>
                                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    {token !== null ? (
                                                        <div>
                                                            <li><Link className="dropdown-item" to="" data-bs-toggle="modal"
                                                                data-bs-target="#staticBackdrop">Đổi mật khẩu</Link></li>
                                                            <li><hr className="dropdown-divider" /></li>
                                                            <li><Link className="dropdown-item" to="/" onClick={btnLogout}>
                                                                Đăng xuất</Link>
                                                            </li>
                                                        </div>
                                                    ) : (
                                                        <li><Link className="dropdown-item" to="/login" >Đăng nhập</Link></li>
                                                    )}


                                                </ul>
                                            </li>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </nav>

                        {/* <!-- Modal --> */}
                        <div className="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <h5 className="modal-title" id="staticBackdropLabel">Đổi mật khẩu</h5>
                                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>

                                    <form onSubmit={handleSubmit(onHandleSubmit)}>
                                        <div className="row modal-body text-start">
                                            <div className="col">
                                                <div className="mt-2">
                                                    <label htmlFor="email" className="form-label">Email:</label>
                                                    <input type="text" className="form-control" id="email" name="email" placeholder="an@gmail.com" value={account.email}
                                                        readOnly />
                                                    <div>{errors.password && <span className="text-white">.</span>}</div>
                                                </div><br />

                                                <div className="mt-2">
                                                    <label htmlFor="newPassword" className="form-label">Mật khẩu mới:</label>
                                                    <input type="password" className="form-control" id="newPassword" name="newPassword"
                                                        {...formPWnewPassword}
                                                        onChange={(e) => {
                                                            formPWnewPassword.onChange(e);
                                                        }}
                                                    /><div>{errors.newPassword && <span className="text-danger">{errors.newPassword.message}</span>}</div>
                                                </div>

                                                <br />



                                            </div>
                                            <div className="col">
                                                <div className="mt-2">
                                                    <label htmlFor="password" className="form-label">Mật khẩu hiện tại:</label>
                                                    <input type="password" name="password" className="form-control" id="password"
                                                        {...formPWpassword}
                                                        onChange={(e) => {
                                                            formPWpassword.onChange(e);
                                                        }}
                                                    /><div>{errors.password && <span className="text-danger">Nhập mật khẩu</span>}</div>
                                                </div>
                                                <br />
                                                <div className="mt-2">
                                                    <label htmlFor="cpNewPassword" className="form-label">Nhập lại mật khẩu mới:</label>
                                                    <input type="password" className="form-control" id="cpNewPassword" name="cpNewPassword"
                                                        {...formPWcpNewPassword}
                                                        onChange={(e) => {
                                                            formPWcpNewPassword.onChange(e);
                                                        }}
                                                    /><div>{errors.cpNewPassword && <span className="text-danger">{errors.cpNewPassword.message}</span>}</div>
                                                </div>

                                                <br />
                                            </div>
                                        </div>

                                        <div className="modal-footer">
                                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                                            <button type="submit" className="btn btn-primary">Đổi mật khẩu</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                        {/* <!-- Modal --> */}
                        
                        {/* than trang */}
                        <div className="container py-3">
                            <article>
                                <Typography component="div" >
                                    <Switch>
                                        <Route path="/dentist-infomation">
                                            <DentistInformation />
                                        </Route>
                                        <Route path="/statistcal">
                                            <Statistical />
                                        </Route>
                                        <Route path="/work-schedule">
                                            <WorkSchedule />
                                        </Route>
                                        <Route path="/login">
                                            <Login setIsLogin={setIsLogin}
                                                isLogin={isLogin} />
                                        </Route>
                                    </Switch>
                                </Typography>
                            </article>
                        </div>
                        {/* than trang */}

                    </main>
                    {/* container */}
                </div>


            </div>
        </BrowserRouter>

    );
}