import request from '@/utils/request'

// 查询所有复学申请列表
export function listAllReinstatementApplications(query) {
  return request({
    url: '/oversea/reinstatementApplication/listAll',
    method: 'get',
    params: query
  })
}

// 查询复学申请详细信息
export function getReinstatementApplicationById(applicationId) {
  return request({
    url: '/oversea/reinstatementApplication/detail',
    method: 'get',
    params: { applicationId }
  })
}

// 添加复学申请
export function addReinstatementApplication(data) {
  return request({
    url: '/oversea/reinstatementApplication/add',
    method: 'post',
    data: data
  })
}

// 更新复学申请信息
export function updateReinstatementApplication(data) {
  return request({
    url: '/oversea/reinstatementApplication/update',
    method: 'post',
    data: data
  })
}

// 更新学院审核结果
export function updateCollegeReview(data) {
  return request({
    url: '/oversea/reinstatementApplication/update/college',
    method: 'post',
    data: data
  })
}

// 更新学校审核结果
export function updateUniversityReview(data) {
  return request({
    url: '/oversea/reinstatementApplication/update/university',
    method: 'post',
    data: data
  })
}

// 删除复学申请
export function deleteReinstatementApplication(applicationId) {
  return request({
    url: '/oversea/reinstatementApplication/delete',
    method: 'get',
    params: { applicationId }
  })
}

// 根据学生ID查询复学申请列表
export function listReinstatementApplicationsByUserId(userId, query) {
  return request({
    url: '/oversea/reinstatementApplication/listByUserId',
    method: 'get',
    params: { userId, ...query }
  })
}

// 根据审核状态查询复学申请列表
export function listReinstatementApplicationsByReviewStatus(collegeReviewResult, universityReviewResult, query) {
  return request({
    url: '/oversea/reinstatementApplication/listByReviewStatus',
    method: 'get',
    params: { collegeReviewResult, universityReviewResult, ...query }
  })
}
