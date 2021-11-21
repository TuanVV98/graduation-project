import React, { useEffect, useState } from 'react';
import axios from 'axios';
import http from '../../service/http-common'
import { formatDate } from '../../../utils/moment-helper';

export default function ListComment({ listComment, setListComment, idxPost1 }) {

    const postId = idxPost1;
    // console.log("page1"+postId);

    //PAGiNATE
    // const [page, setPage] = useState(0);
    let page = 0;
    const[limit,setLimit]=useState(3);


    const [filterData, setFilterData] = useState([]);
    useEffect(() => {
        const url = `http://localhost:8080/api/v1/comment/post?postsId=${postId}`;
        axios({
            url: url,
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setListComment(data.data);
                setFilterData(data.data.slice(0, 3));
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, [postId]);

    //get account
    const [listAccount, setListAccount] = useState([]);
    useEffect(() => {
        //  if (typeof (listComment.id) !== 'undefined' && listComment.id != null) {
        // console.log(auth.id)
        http({
            url: '/accounts',
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setListAccount(data.data);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
        //  }

    }, [listComment]);



    const onChangeHandler = (event) => {
        const { value } = event.target;
        setLimit(value);
    }

    useEffect(() => {
        //page*limit=số bản ghi bỏ qua(vị trí start); page*limit+limit=vị trí end
        //cắt 1 mảng
        setFilterData(listComment.slice(page * limit, page * limit + limit));
    }, [page,limit,listComment])


    return (
        <div className="col-12 row mb-2">
            <div className="d-flex justify-content-end">
                <div className="col-3">
                    <select className="form-select"
                        onChange={onChangeHandler}>
                        <option value="3">Hiển thị bình luận</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="15">25</option>
                        <option value="15">50</option>
                        <option value="15">100</option>


                    </select>

                </div>

            </div>
            {
                filterData.map((val, idx) => {
                    return (
                        <div className="card border border-white" key={idx}>
                            <hr className=" border border border-1" />
                            <div className="card-body ">
                                <img src="https://mdbootstrap.com/img/new/fluid/nature/020.jpg"
                                    className="img-fluid rounded-circle float-start me-1" alt="..."
                                    style={{ width: '4rem', height: '4rem' }} />
                                <i className='fas fa-exclamation-circle 
                                    d-flex justify-content-end' style={{ color: 'blue' }}></i>
                                <h5 className="card-title">
                                    {listAccount.map((obj) => {
                                        return obj.id === val.accountsId ?
                                            obj.email.substring(0, obj.email.indexOf("@")) : null;

                                    })}
                                </h5>
                                <p className="card-text">
                                    {val.content}
                                </p>
                                <p className="card-text">
                                    <small className="text-muted">{formatDate(val.createAt)}</small>
                                </p>
                            </div>
                        </div>
                    )
                })
            }
        </div>
    )
}