import Comment from "./Comment";
import ListComment from "./ListComment";
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ImgHeaderNews from './ImgHeaderNews'
import { useLocation } from "react-router";
import { Link } from "react-router-dom";
import http from "../../service/http-common"

function DetailedNews() {
    //lấy Params trên URL
    let location = useLocation();
    let query = new URLSearchParams(location.search);
    let idPostParam = query.get('id');

    const [listPost, setListPost] = useState([]);
    const [post, setPost] = useState([]);
    const [idxPost, setIdxPost] = useState(idPostParam !== null ? Number(idPostParam) : 1);
    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";
    const [page, setPage] = useState(0);
    const [filterPost, setFilterPost] = useState([]);
    const [listComment, setListComment] = useState([]);
    let token = localStorage.getItem("token");


    //dowload listPost
    useEffect(() => {
        const url = `http://localhost:8080/api/v1/posts`;
        axios({
            url: url,
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setListPost(data.data);
                setFilterPost(data.data.slice(0, 5));

            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, []);

    //dowload post by id
    useEffect(() => {
        const url = `http://localhost:8080/api/v1/posts/${idxPost}`;
        axios({
            url: url,
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setPost(data.data);

            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, [idxPost]);

    //CLICK SHOW Post
    const onClickHandler = (val, idx) => {
        setIdxPost(idx);
    }





    // PAGINATE
    //số bản ghi được phân trang
    const limit = 5;
    const size = Math.ceil(1.0 * listPost.length / limit);


    const btnFirst = () => {
        setPage(0);
    }

    const btnPrevious = () => {
        if (listPost.length > 0) {
            setPage(page - 1);
            if (page === 0) {
                setPage(size - 1);
            }
        }

    }

    const btnNext = () => {

        setPage(page + 1);
        if (page >= size - 1) {
            setPage(0);

        }

    }

    const btnLast = () => {
        if (listPost.length > 0) {
            setPage(size - 1);
        }

    }

    useEffect(() => {
        //page*limit=số bản ghi bỏ qua(vị trí start); page*limit+limit=vị trí end
        //cắt 1 mảng
        setFilterPost(listPost.slice(page * limit, page * limit + limit));
    }, [page,listPost]);
    // END PAGINATE

    //SEARCH
    const [keyword, setKeyWord] = useState('');
    useEffect(() => {

        setFilterPost(
            keyword.trim().length > 0 ?
                listPost.filter((obj) => {
                    return obj.title.toLowerCase()
                        .includes(keyword.toLowerCase());
                })
                :
                listPost.slice(page * limit, page * limit + limit).filter((obj) => {
                    return obj.title.toLowerCase()
                        .includes(keyword.toLowerCase());
                })
        );
    }, [keyword, listPost,page]);


    //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
    const [auth, setAuth] = useState({});
    useEffect(() => {
        let json = localStorage.getItem("auth");
        if (json != null && typeof (json) !== 'undefined') {
            setAuth(JSON.parse(json));
        }
    }, [])

    //dowload like by id post(đếm like)
    const [listLike, setListLike] = useState([]);
    useEffect(() => {
        const url = `http://localhost:8080/api/v1/likes/all/${idxPost}`;
        axios({
            url: url,
            method: 'GET',
        })
            .then((response) => {
                const { data } = response;
                setListLike(data.data);
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, [idxPost]);

    //tim like theo acc vs post(check xem user like hay k)
    // const [checkLike, setCheckLike] = useState({});
  
    // useEffect(() => {
    //     if (typeof (auth.id) !== 'undefined' && auth.id != null) {
    //     const url = `http://localhost:8080/api/v1/likes/acc/${auth.id}/post/${idxPost}`;
    //     // console.log(url)
    //     http({
    //         url: url,
    //         method: 'GET',
    //     })
    //         .then((response) => {
    //             const { data } = response;
    //             if(data.data!=={}){
    //                 setCheckLike(data.data);
    //             }else{
    //                 setCheckLike({});
    //             }
                
    //             // setColorLike("text-info")
                
    //         })
    //         .catch((error) => {
    //             // setColorLike("text-dark")
    //             console.log(error, error.response);
    //         });
    //     }
    // }, [idxPost,auth]);
    // const [colorLike, setColorLike] = 
    // useState(checkLike?'text-info':'text-dark');
    // console.log(checkLike?"1":"2");

    //data khi like
    const [dataLike, setDataLike] = useState({
        createAt: "",
        posts: {
            id: idxPost

        },
        accounts: {
            id: auth.id
        }
    });
   
    //tải account_id vào dataLike
    useEffect(()=>{
        if(typeof (auth.id) !== 'undefined' && auth.id != null){
            setDataLike((oldState)=>{
                return{
                    ...oldState,
                    accounts: {
                        id: auth.id,
                    }
                }
            })
        }
    },[auth])
   
    //add like vào db

    const onAddLike = (data) => {
        const url = `http://localhost:8080/api/v1/likes`;
        http({
            url: url,
            method: 'POST',
            data: data
        })
            .then((response) => {
                const { data } = response;
                setListLike([
                    ...listLike,
                    data.data
                ])
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }


    //delete like
    const onDeleteLike=(index) => {
       
        const url = 'http://localhost:8080/api/v1/likes/'+listLike[index].id;
        http({
            url: url,
            method: 'DELETE'
        })
            .then((response) => {
                if (response.status === 200) {
                    setListLike((oldState)=>{
                        return oldState.filter((val,idx)=>{
                            return idx===index?false:true;
                        });
                    });
                }
            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }

    
   
    let index=listLike.findIndex(a=>a.posts.id===idxPost&& a.accounts.id===auth.id);
    let colorLike="black";
    let messLike="";
    if(index===-1){
        colorLike="black";
        messLike="";
    }else{
        colorLike="blue";
        messLike="đã thích";;
    }
    console.log(colorLike);
    //xử lý action like và unlike
    const btnLike=()=>{
        // console.log(dataLike);
        
        console.log(index)
            // document.getElementById("colorLike").style.color="black";
        if(index===-1){
            console.log("ADD LIKE")
           onAddLike(dataLike);
            // index=document.getElementById("colorLike").style.color="blue";

            // idx=-1;
            
        }else{
            console.log("DELETE LIKE")
            onDeleteLike(index);
            // document.getElementById("colorLike").style.color="black";

        }
    
    }



    return (
        <div style={{ minHeight: '1000px' }}>
            {/* Header */}
            <div className="text-center">
                <ImgHeaderNews />
            </div>
            {/* End Header */}
            <div className="container">
                <div className="col-12 row mt-5 d-flex justify-content-center">

                    {/* CATEGORY  */}
                    <div className="col-md-3 mb-3">
                        <input className="form-control me-2" type="search"
                            placeholder="Tìm kiếm tiêu đề" aria-label="Search"
                            onChange={(event) => setKeyWord(event.target.value)} value={keyword} />
                        <br />
                        <h4 className="mt-2">Tin tức mới</h4>
                        <div className="card mt-2" >
                            <ul className="list-group list-group-flush">
                                {
                                    filterPost.map((val, idx) => {
                                        return (
                                            <li className="list-group-item" key={idx} onClick={() => onClickHandler(val, val.id)}>
                                                <img src={srcUrl + val.image}
                                                    className="img-fluid rounded-circle float-start me-1" alt="..."
                                                    style={{ width: '4rem', height: '4rem' }} />
                                                <a className="fw-bold text-decoration-none text-dark" href={`/news?id=` + val.id}>
                                                    {val.title}
                                                </a>
                                            </li>
                                        )
                                    })
                                }
                            </ul>
                            {/* PAGINATION */}
                            <div className="mt-2">
                                <ul className="pagination justify-content-center">
                                    <li className="page-item" >
                                        <button className="page-link" onClick={btnFirst}>
                                            <i className='fas fa-angle-left text-dark'></i>
                                        </button>
                                    </li>
                                    <li className="page-item" >
                                        <button className="page-link" onClick={btnPrevious}>
                                            <i className='fas fa-angle-double-left text-dark'></i>
                                        </button>
                                    </li>
                                    <li className="page-item">
                                        <button className="page-link text-dark">
                                            {listPost.length > 0 ? page + 1 : 0}/{size}
                                        </button>
                                    </li>
                                    <li className="page-item" >
                                        <button className="page-link" onClick={btnNext}>
                                            <i className='fas fa-angle-double-right text-dark' ></i>
                                        </button>
                                    </li>
                                    <li className="page-item" >
                                        <button className="page-link" onClick={btnLast}>
                                            <i className='fas fa-angle-right text-dark'></i>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                            {/* END PAGINATION */}
                        </div>
                    </div>
                    {/* END CATEGORY */}


                    {/* CONTENT  */}
                    <div className="col-md-8">

                        <div className="card mb-3"
                            style={{
                                minHeight: '40rem',
                                // backgroundColor:'rgb(246, 249, 249)' 
                            }}>
                            <div className="card-body">
                                <img src={srcUrl + post.image}
                                    alt="..."
                                    style={{ width: '15.8rem',height:'15rem' }} 
                                    className="rounded float-start me-3 img-fluid" />
                                <h5 className="card-title">{post.title}</h5>
                                <p className="card-text">
                                    {post.content}
                                </p>
                                {
                                    token === null ? (
                                        <Link to="/account/login" className="text-dark text-decoration-none">
                                            <i className='far fa-thumbs-up fs-3'> {listLike.length}</i></Link>

                                    ) : (<i className="far fa-thumbs-up fs-3" id="colorLike"
                                                style={{color:`${colorLike}`}}
                                            onClick={btnLike}>{listLike.length} {messLike} </i>)
                                }

                                {/* <i className='far fa-thumbs-down ms-2 fs-3'></i> */}
                            </div>


                        </div>
                        <Comment idxPost={idxPost}
                            listComment={listComment}
                            setListComment={setListComment} />
                        <ListComment idxPost1={idxPost}
                            listComment={listComment}
                            setListComment={setListComment} />
                    </div>
                    {/*END CONTENT  */}
                </div>
            </div>
        </div>
    )
}
export default DetailedNews;