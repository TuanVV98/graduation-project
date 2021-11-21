import React from 'react';
import TextField from '@material-ui/core/TextField';
import { useState, useEffect } from "react";
import axios from 'axios'
import { useForm } from "react-hook-form";
import http from '../../service/http-common';

export default function FormUserInformation() {


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





    /// get user

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


    const [formData, setFormData] = useState({
        id: "",
        accounts: {
            id: auth.id,
        },
        image: "",
        fullname: "",
        birthday: "",
        gender: "",
        communes: {
            id: "",
        },
        telephone: "",
        story: "",
        updateAt: "",
        deleteAt: false,
    });

    //bat su kien khi input thay doi
    const onChangeHandler = (event) => {
        event.preventDefault()
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
        console.log(formData);
    }

    //get customer by acc id
    const [checkInfo, setCheckInfo] = useState("0");
    useEffect(() => {
        if (typeof (auth.id) !== 'undefined' && auth.id !== null) {
            http({
                url: `http://localhost:8080/api/v1/customers/accounts/${auth.id}`,
                method: 'GET',
            })
                .then((response) => {
                    const { data } = response;
                    if (data !== null) {
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

    //upanh
    const image = (event) => {
        const { files } = event.target;
        var form = new FormData();
        form.append("image", files[0]);

        http({
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



    /// create update
    const onCreateCustomer = () => {
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
            url: `http://localhost:8080/api/v1/customers`,
            method: 'POST',
            data: formData,
        })
            .then((response) => {
                // const { data } = response;
                alert("Bổ xung thông tin thành công");
                window.location.reload();
            })
            .catch((error) => {
                console.log(error.response);
                alert("Bổ xung thông tin thất bại")
            })
    }

    const onUpdateCustomer = () => {
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
                url: `http://localhost:8080/api/v1/customers/${formData.id}`,
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


    // cuoi \
    const { register, handleSubmit, formState: { errors } } = useForm(
    );

    const onHandleSubmit = (data) => {
        // console.log(data);
        if (checkInfo === "0") {
            //CREATE
            onCreateCustomer();
        } else {
            //UPDATE
            onUpdateCustomer();
        }
    }

    //validate
    const formDataFullName = register("fullname", { required: "Tên bị trống" });
    const formDataBirthday = register("birthday", { required: "Chọn ngày sinh" });
    const formDataTelephone = register("telephone", {
        required: "Nhập số điện thoại",
        pattern: { value: /((0)+([0-9]{9})\b)/g, message: "Nhập số điện thoại đúng định dạng" },
    });
    const formDataStory = register("story", { required: "Nhập tiểu sử" });
    const formDataGender = register("gender", { required: true });
    const formDataProvinces = register("provinces", { required: true });
    const formDataDistricts = register("districts", { required: true });
    const formDataCommunes = register("communes", { required: true });

    return (
        <div className="pt-4 mt-5 text-center" style={{ backgroundColor: 'rgb(246, 249, 249)', minHeight: '750px' }}>
            <div></div>
            <div className="container">

                <form onSubmit={handleSubmit(onHandleSubmit)}>

                    <div className="row text-start d-flex justify-content-center">
                        <div className="col-sm-3 mx-5">

                            <div>
                                <label htmlFor="email" className="form-label">Email:</label>
                                <input type="email" className="form-control" value={account.email} readOnly />
                            </div><br />

                            <div>
                                <label htmlFor="fullName" className="form-label">Họ và tên:</label>
                                <input type="text"
                                    value={formData.fullname}
                                    name="fullname"
                                    id="fullname" className="form-control"
                                    {...formDataFullName}
                                    onChange={(e) => {
                                        formDataFullName.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                />
                                {errors.fullname && <span className="text-danger">{errors.fullname.message}</span>}
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

                            <div className="mt-2">
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
                                <label htmlFor="story" className="form-label">Tiểu sử:</label>
                                <textarea className="form-control" id="story" rows="3"
                                    value={formData.story}
                                    name="story"
                                    {...formDataStory}
                                    onChange={(e) => {
                                        formDataStory.onChange(e);
                                        onChangeHandler(e);
                                    }}
                                ></textarea>
                                {errors.story && <span className="text-danger">{errors.story.message}</span>}
                            </div><br />

                        </div>

                        <div className="col-sm-3 mx-5">

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

                    <div>
                        <button className="btn btn-primary mt-3 mb-5" type="submit">Cập nhật</button>
                    </div>
                </form>
            </div>
        </div>
    )
}