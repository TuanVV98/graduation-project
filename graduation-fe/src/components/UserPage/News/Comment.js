import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import http from "../../service/http-common"
import { useEffect,useState } from "react";
export default function Comment({idxPost,listComment,setListComment}) {
    const { register, handleSubmit, formState: { errors } } = useForm();
    let token = localStorage.getItem("token");

    //GET DATA NGƯỜI DÙNG TỪ LOCAL STORAGE(ID,ROLE)
    const [auth, setAuth] = useState({});
    useEffect(() => {
        let json = localStorage.getItem("auth");
        if (json != null && typeof (json) !== 'undefined') {
            setAuth(JSON.parse(json));
        }
    }, [])


   
    const onHandleSubmit=(data)=>{
        let formData={
            content: data.content,
            image: "no-image",
            postsId: idxPost,
            accountsId: auth.id,
        }
        // console.log(formData) 
        http({
            method:'POST',
            url:'/comment',
            data:formData
        })
            .then((response)=>{
                const{data}=response;
                console.log('COMMENT SUCCESS')
                setListComment([
                    ...listComment,
                    data.data
                ])
                document.getElementById('exampleFormControlTextarea1').value=""
            })
            .catch((error)=>{
                console.log(error,error.response)
            })
    }


    return (
        <div className="col-12 row" >
            {/* <div className="col-6 offset-3 mb-5"> */}
                <hr className="ms-2 border border-1"/>

                <form className="ms-2 border border-white" onSubmit={handleSubmit(onHandleSubmit)}>
                    <span className="fs-5">Ý kiến</span><br />
                    <span className="fw-light">Tên của bạn sẽ không được hiển thị công khai. </span><br />
                    <div className="mt-2">
                            <label htmlFor="exampleFormControlTextarea1" className="form-label">
                                Bình luận
                            </label>
                            <textarea className="form-control" id="exampleFormControlTextarea1"
                             rows="3" {...register("content",{required:"Không để trống bình luận",
                             maxLength:{value:2000,message:"Bình luận không quá 2000 ký tự"}
                             })}>
                            </textarea>
                            {errors.content&&<span className="text-danger">{errors.content.message}</span>}
                            <br/>

                            {token===null?(
                                <Link to="/account/login" className="btn btn-info mt-2">Bình luận</Link>
                            
                            ):(<button type="submit" className="btn btn-info mt-2">Bình luận</button>)}
                    </div>
                </form>

            {/* </div> */}
        </div>
    );
}