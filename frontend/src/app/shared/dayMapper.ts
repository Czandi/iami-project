export class DateMapper {
  public static mapDayNumberToText(number: Number): String {
    switch (number) {
      case 0:
        return 'Poniedziałek';
      case 1:
        return 'Wtorek';
      case 2:
        return 'Środa';
      case 3:
        return 'Czwartek';
      case 4:
        return 'Piątek';
      case 5:
        return 'Sobota';
      case 6:
        return 'Niedziela';
    }
  }

  public static mapDayToNumber(day: String): Number {
    switch (day) {
      case 'poniedziałek':
        return 0;
      case 'wtorek':
        return 1;
      case 'środa':
        return 2;
      case 'czwartek':
        return 3;
      case 'piątek':
        return 4;
      case 'sobota':
        return 5;
      case 'niedziela':
        return 6;
    }
  }
}
