export default function BinhLuan() {
    return (
        <div className="col-12 row" >
            <div className="col-6 offset-3 mb-5">
                <hr className="ms-5 border border-1"/>
                <form className="ms-5 border border-white" >
                    <span className="fs-5">Ý kiến</span><br />
                    <span className="fw-light">Tên của bạn sẽ không được hiển thị công khai. </span><br />
                    <div className="mt-2">
                        <label htmlFor="exampleFormControlTextarea1" className="form-label">
                            Bình luận
                        </label>
                        <textarea className="form-control" id="exampleFormControlTextarea1" rows="3">
                        </textarea>
                        <button type="submit" className="btn btn-info mt-2">Bình luận</button>
                    </div>
                </form>
            </div>
        </div>
    );
}