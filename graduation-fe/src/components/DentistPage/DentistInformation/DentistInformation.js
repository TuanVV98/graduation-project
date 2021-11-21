import React from 'react';
import TextField from '@material-ui/core/TextField';
import { useState, useEffect } from "react";
import axios from 'axios'
import { useForm } from "react-hook-form";
import http from '../../service/http-common';

export default function DentistInformation() {
    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";
    const [provinces, setPprovinces] = useState([{
        id: "",
        name: "",
        type: ""
    }]);
    const [districts, setDistricts] = useState([{

        id: "",
        name: "",
        type: "",
        provinces: {
            id: "",
        }

    }]);
    const [commune, setCommune] = useState([{
        id: "",
        name: "",
        type: "",
        districts: {
            id: "",
        }
    }]);

    useEffect(() => {
        axios({
            url: 'http://localhost:8080/api/v1/provinces',
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setPprovinces(data.data);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, []);

    useEffect(() => {
        axios({
            url: 'http://localhost:8080/api/v1/districts',
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setDistricts(data.data);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, []);

    useEffect(() => {
        axios({
            url: 'http://localhost:8080/api/v1/communes',
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setCommune(data.data);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, []);

    /// set dia chi
    const [provincesID, setProvincesID] = useState("");
    const [districtsID, setDistrictsID] = useState("");

    // change provincesID
    const changeProvincesID = (event) => {
        const key = event.target.value;
        setProvincesID(key);
        if (key === "") {
            setDistrictsID("");
            formData.communes.id = "";
        }
    }

    const [listDistricts, setListDistricts] = useState([]);
    useEffect(() => {
        setListDistricts(
            districts.filter((obj) => {
                return obj.provinces.id.includes(provincesID);
            }
            )
        )
    }, [provincesID, districts]);

    // change districtsID
    const changeDistrictsID = (event) => {
        const key = event.target.value;
        setDistrictsID(key);
        if (key === "") {
            formData.communes.id = ""
        }
    }

    const [listCommunes, setListCommunes] = useState([]);
    useEffect(() => {
        setListCommunes(
            commune.filter((obj) => {
                return obj.districts.id.includes(districtsID);
            }
            )
        )
    }, [districtsID, commune]);



    ////get dentist

    //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
    const [auth, setAuth] = useState({
    });
    useEffect(() => {
        let json = localStorage.getItem("auth");
        if (json != null && typeof (json) !== 'undefined') {
            setAuth(JSON.parse(json));
        }
    }, [])
    ///
    //LẤY THÔNG TIN CHI TIẾT NGƯỜI DÙNG KHI CÓ ACCOUNT_ID
    const [account, setAccount] = useState({
        email: "",
    });
    useEffect(() => {
        if (typeof (auth.id) !== 'undefined' && auth.id != null) {
            http({
                url: '/accounts/' + auth.id,
                method: 'GET',
            })
                .then((response) => {
                    const { data } = response;
                    setAccount(data.data);
                })
                .catch((error) => {
                    console.log(error, error.response);
                });
        }

    }, [auth]);



    /////

    const [formData, setFormData] = useState({
        id: "",
        accounts: {
            id: auth.id,
        },
        image: "",
        cccd: "",
        fullName: "",
        birthday: "",
        gender: "",
        communes: {
            id: "",
        },
        telephone: "",
        exp: "",
        updateAt: "",
        deleteAt: false,
    });
    ////
    //bat su kien khi input thay doi
    const onChangeHandler = (event) => {
        event.preventDefault()
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
        // console.log(formData);
    }

    //
    //get dentist by acc id
    const [checkInfo, setCheckInfo] = useState("0");
    useEffect(() => {
        if (typeof (auth.id) !== 'undefined' && auth.id != null) {
            http({
                url: `http://localhost:8080/api/v1/dentists/accounts/${auth.id}`,
                method: 'GET',
            })
                .then((response) => {
                    const { data } = response;
                    if (data != null) {
                        setCheckInfo("1");
                        setFormData(data.data);
                        setProvincesID(data.data.communes.districts.provinces.id);
                        setDistrictsID(data.data.communes.districts.id);
                    }
                })
                .catch((error) => {
                    console.log(error, error.response);
                });
        }
    }, [auth]);




    //
    //upanh
    const image = (event) => {
        const { files } = event.target;
        var form = new FormData();
        form.append("image", files[0]);

        axios({
            url: `http://localhost:8080/api/v1/files/upload`,
            method: 'POST',
            data: form,
            headers: {
                "Content-Type": "multipart/form-data",
            }
        })
            .then((response) => {
                const { data } = response;
                if (data != null) {
                    setFormData({
                        ...formData,
                        image: data
                    });
                }
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }
    ///

    /// create update
    const onCreateDentist = () => {
        console.log("create");
        if (typeof (auth.id) !== 'undefined' && auth.id != null) {
            formData.accounts.id = auth.id;
        }
        if (formData.gender === "true") {
            formData.gender = true
        } else if (formData.gender === "false") {
            formData.gender = false
        }

        formData.deleteAt = false;
        // console.log(formData);

        http({
            url: `http://localhost:8080/api/v1/dentists`,
            method: 'POST',
            data: formData,
        })
            .then((response) => {
                alert("Bổ xung thông tin thành công");
                window.location.reload();
            })
            .catch((error) => {
                console.log(error.response);
                alert("Bổ xung thông tin thất bại")
            })
    }

    const onUpdateDentist = () => {
        console.log("update");
        const yourDate = new Date()
        formData.updateAt = yourDate.toISOString().split('T')[0];
        if (typeof (auth.id) !== 'undefined' && auth.id != null) {
            formData.accounts.id = auth.id;
        }
        if (formData.gender === "true") {
            formData.gender = true
        } else if (formData.gender === "false") {
            formData.gender = false
        }

        // console.log(formData);

        if (formData.id !== "") {
            http({
                url: `http://localhost:8080/api/v1/dentists/${formData.id}`,
                method: 'PUT',
                data: formData,
            })
                .then((response) => {
                    alert("Cập nhật thông tin thành công");
                })
                .catch((error) => {
                    console.log(error.response);
                    alert("Cập nhật thông tin thất bại");
                });
        }
    }

    /////
    const { register, handleSubmit, formState: { errors } } = useForm(
    );

    const onHandleSubmit = (data) => {
        console.log(data);
        if (checkInfo === "0") {
            //CREATE
            onCreateDentist();
        } else {
            //UPDATE
            onUpdateDentist();
        }
    }

    //validate
    const formDataCccd = register("cccd", {
        required: "Nhập số căn cước công dân",
        pattern: { value: /((0)+([0-9]{11})\b)/g, message: "Nhập số căn cước đúng định dạng" },

    });
    const formDataFullName = register("fullName", { required: "Tên bị trống" });
    const formDataBirthday = register("birthday", { required: "Chọn ngày sinh" });
    const formDataTelephone = register("telephone", {
        required: "Nhập số điện thoại",
        pattern: { value: /((0)+([0-9]{9})\b)/g, message: "Nhập số điện thoại đúng định dạng" },
    });
    const formDataExp = register("exp", { required: "Nhập kinh nghiệm(mô tả)" });
    const formDataGender = register("gender", { required: true });
    const formDataProvinces = register("provinces", { required: true });
    const formDataDistricts = register("districts", { required: true });
    const formDataCommunes = register("communes", { required: true });

    return (
        <div className="pt-4  text-center" style={{ backgroundColor: 'rgb(246, 249, 249)', minHeight: '950px' }}>


            <div className="container">
                <h3 className="">QUẢN LÝ THÔNG TIN CÁ NHÂN</h3><br />
                <form onSubmit={handleSubmit(onHandleSubmit)}>
                    <div className="row text-start d-flex justify-content-center">
                        <div className="col-sm-3 mx-5">

                            <div>
                                <label htmlFor="emailD" className="form-label">Email:</label>
                                <input type="text" className="form-control" id="emailD"
                                    value={account.email}
                                    readOnly />
                            </div><br />

                            <div>
                                <label htmlFor="cccd" className="form-label">Căn cước công dân:</label>
                                <input type="text" className="form-control" id="cccd" placeholder="00120101010205"
                                    value={formData.cccd}
                                    name="cccd"
                                    {...formDataCccd}
                                    onChange={(e) => {
                                        formDataCccd.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                />
                                {errors.cccd && <span className="text-danger">{errors.cccd.message}</span>}
                            </div><br />

                            <div>
                                <label htmlFor="fullName" className="form-label">Họ và tên:</label>
                                <input type="text" className="form-control" placeholder="Nguyen Van An"
                                    value={formData.fullName}
                                    name="fullName"
                                    id="fullName"
                                    {...formDataFullName}
                                    onChange={(e) => {
                                        formDataFullName.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                />
                                {errors.fullName && <span className="text-danger">{errors.fullName.message}</span>}
                            </div>

                            <div className="mt-4">
                                <label htmlFor="birthday" className="form-label">Ngày sinh:</label><br />
                                <TextField
                                    id="birthday"
                                    type="date"
                                    name="birthday"
                                    value={formData.birthday}
                                    {...formDataBirthday}
                                    onChange={(e) => {
                                        formDataBirthday.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                /><br />
                                {errors.birthday && <span className="text-danger">{errors.birthday.message}</span>}
                            </div><br />

                            <div className="mt-2">
                                <label htmlFor="gender" className="form-label">Giới tính:</label><br />
                                <select className="form-select" id="gender" name="gender"
                                    value={formData.gender}
                                    {...formDataGender}
                                    onChange={(e) => {
                                        formDataGender.onChange(e);
                                        onChangeHandler(e);
                                    }}>
                                    <option value="">Chọn giới tính</option>
                                    <option value="true">Nam</option>
                                    <option value="false">Nữ</option>

                                </select>
                                {errors.gender && <span className="text-danger">Chọn giới tính</span>}
                            </div><br />


                            <div>
                                <label htmlFor="exp" className="form-label">Mô tả kinh nghiệm:</label>
                                <textarea className="form-control" id="exp" rows="7"
                                    value={formData.exp}
                                    name="exp"
                                    {...formDataExp}
                                    onChange={(e) => {
                                        formDataExp.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                ></textarea>
                                {errors.exp && <span className="text-danger">{errors.exp.message}</span>}
                            </div><br />

                        </div>

                        <div className="col-sm-3 mx-5">

                            <div >
                                <label htmlFor="telephone" className="form-label">Số điện thoại:</label>
                                <input type="text" className="form-control" id="telephone"
                                    value={formData.telephone}
                                    name="telephone"
                                    {...formDataTelephone}
                                    onChange={(e) => {
                                        formDataTelephone.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                    placeholder="0123..."
                                />
                                {errors.telephone && <span className="text-danger">{errors.telephone.message}</span>}
                            </div><br />

                            <div>
                                <label htmlFor="provinces" className="form-label">Tỉnh/Thành Phố:</label>
                                <select className="form-select" id="provinces"
                                    name="provinces"
                                    value={provincesID}
                                    {...formDataProvinces}
                                    onChange={(e) => {
                                        formDataProvinces.onChange(e);
                                        changeProvincesID(e);
                                    }}
                                >
                                    <option value="">Chọn thành phố</option>
                                    {
                                        provinces.map((val, idx) => {
                                            return (
                                                <option key={idx} value={val.id}
                                                >{val.name}</option>
                                            )
                                        })
                                    }
                                </select>
                                {errors.provinces && <span className="text-danger">Chọn thành phố</span>}
                            </div><br />

                            <div>
                                <label htmlFor="districts" className="form-label">Quận/Huyện:</label>
                                <select className="form-select" aria-label="Default select example" id="districts" name="districts"
                                    disabled={provincesID === ""}
                                    value={districtsID}
                                    {...formDataDistricts}
                                    onChange={(e) => {
                                        formDataDistricts.onChange(e);
                                        changeDistrictsID(e);
                                    }}

                                >
                                    <option value="">Chọn huyện</option>
                                    {
                                        listDistricts.map((val, idx) => {
                                            return (
                                                <option key={idx}
                                                    value={val.id}>{val.name}</option>
                                            )
                                        })
                                    }
                                </select>
                                {errors.districts && <span className="text-danger">Chọn quận/huyện</span>}
                            </div><br />


                            <div>
                                <label htmlFor="communes" className="form-label">Phường/Xã:</label>
                                <select className="form-select" aria-label="Default select example" id="communes" name="communes"
                                    disabled={districtsID === ""}
                                    value={formData.communes.id}
                                    {...formDataCommunes}
                                    onChange={(e) => {
                                        formDataCommunes.onChange(e);
                                        onChangeHandler(e);
                                        setFormData({
                                            ...formData,
                                            communes: {
                                                id: e.target.value
                                            }
                                        })
                                    }}
                                >
                                    <option value="">Chọn xã</option>

                                    {
                                        listCommunes.map((val, idx) => {
                                            return (
                                                <option key={idx}

                                                    value={val.id}>{val.name}</option>
                                            )
                                        })
                                    }
                                </select>
                                {errors.communes && <span className="text-danger">Chọn xã</span>}
                            </div><br />

                            <div>
                                <label htmlFor="image" className="form-label">Chọn ảnh:</label>
                                <input className="form-control" type="file" id="image" name="image" onChange={(e) => { image(e) }}
                                />
                                {errors.image && <span className="text-danger">Chưa chọn ảnh</span>}
                            </div><br />

                            <div>
                                <label htmlFor="image" className="form-label">Hình ảnh:</label><br />
                                <img id="image" src={srcUrl + formData.image} className="card-img" alt="no img" style={{ height: '170px', width: '150px' }}></img>
                            </div>

                        </div>
                    </div>
                    <br />
                    <div>
                        <button type="submit" className="btn btn-primary mb-5">Cập nhật</button>
                    </div>
                </form>
            </div>
        </div>

    )
}