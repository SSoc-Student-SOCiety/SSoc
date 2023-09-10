export const useCurrentDate = () => {
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더하고 두 자리로 포맷
  const day = String(today.getDate()).padStart(2, "0"); // 날짜를 두 자리로 포맷
  return { year, month, day, date: today };
};
