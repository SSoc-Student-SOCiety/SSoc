export const useCurrentWeekNumber = () => {
  const today = new Date();
  const month = today.getMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.
  const year = today.getFullYear();

  // 현재 날짜의 첫 번째 날을 구합니다.
  const firstDayOfMonth = new Date(year, month - 1, 1);

  // 현재 날짜의 요일을 구합니다. (0: 일요일, 1: 월요일, ..., 6: 토요일)
  const dayOfWeek = firstDayOfMonth.getDay();

  // 첫 번째 주의 시작일을 구합니다.
  const firstWeekStartDate = new Date(firstDayOfMonth);
  firstWeekStartDate.setDate(1 - dayOfWeek);

  // 현재 날짜의 날짜를 구합니다.
  const currentDay = today.getDate();
  const weekNumber = Math.ceil((currentDay + dayOfWeek) / 7);

  return { weekNumber };
};
