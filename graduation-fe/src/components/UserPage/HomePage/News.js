//form "hiển thị các thông tin bài viết"
import { makeStyles } from '@material-ui/core/styles';
import { useState, useEffect } from "react";
import axios from 'axios'
import { Link } from 'react-router-dom';
function News() {

    //css title
    const useStyles = makeStyles((theme) => ({
        title: {
            fontSize: '2.2em',
            marginBottom: '0.2em',
            color: '#1c1c1c',
            fontWeight: 300,
            fontFamily: 'Roboto'
        },

        block: {
            color: 'black',
            textDecoration: 'none',
        },

        '&:hover': {
            boxShadow: '3px 3px 5px 6px #ccc',
            // backgroundColor: 'white',
            // color: '#00bcd5',


        }

    }));
    const classes = useStyles();




    const [listData, setListData] = useState([]);
    const [filterData, setFilterData] = useState([]);
    const [page, setPage] = useState(0);


    const limit = 3;
    //số bản ghi được phân trang
    const size = Math.ceil(1.0 * listData.length / limit);



    const btnPrevious = () => {
        if (listData.length > 0) {
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



    useEffect(() => {
        //page*limit=số bản ghi bỏ qua(vị trí start); page*limit+limit=vị trí end
        //cắt 1 mảng
        setFilterData(listData.slice(page * limit, page * limit + limit));
    }, [page,listData])

    useEffect(() => {

        axios({
            url: 'http://localhost:8080/api/v1/posts',
            method: 'GET',

        })
            .then((response) => {
                const { data } = response;
                setListData(data.data);
                setFilterData(data.data.slice(0, 3));

            })
            .catch((error) => {
                console.log(error, error.response);
            });
    }, []);

    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";

    return (
        <div className="text-center pt-4 mt-5" style={{ backgroundColor: 'rgb(246, 249, 249)', minHeight: '700px' }}>
            {/* title */}
            <div>
                <h1 className={classes.title}>Tin tức nha khoa thế giới</h1>
                <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
            </div>
            <br /><br />
            <div id="carouselExampleControlsTinTuc" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-inner">

                    {/* CONTENT 1 */}


                    <div className="carousel-item active">
                        <div className="card-group mx-5">

                            {filterData.map((val, idx) => {
                                return (
                                    <Link className="clearfix card ms-3 text-decoration-none"
                                        style={{ color: 'black' }}
                                        to={`/news?id=` + val.id} key={idx}>
                                        <div className="">
                                            <img src={srcUrl + val.image}
                                                className="card-img-top" alt="..." style={{ height: '14rem' }} />
                                            <div className="card-body">
                                                <h5 className="card-title">{val.title}</h5>
                                                <p className="card-text">{val.content.substring(0, 200)}</p>
                                            </div>

                                        </div>

                                    </Link>

                                )
                            })
                            }

                        </div>
                    </div>


                    {/* END CONTENT 1 */}






                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsTinTuc"
                    data-bs-slide="prev" onClick={btnPrevious}>
                    <span className="carousel-control-prev-icon btn btn-secondary btn-lg" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button"
                    data-bs-target="#carouselExampleControlsTinTuc" data-bs-slide="next"
                    onClick={btnNext}>
                    <span className="carousel-control-next-icon btn btn-secondary btn-lg" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    )
}
export default News;