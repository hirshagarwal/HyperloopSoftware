#include <iostream>
#include <vector>
#include "ErrorCorrector.h"

using namespace std;

int main() {

	//Fields
	double in1 = 0;
	double in2 = 0;
	double in3 = 0;

		//Dummy Readings
	vector<double> readings;

	//Get Values From User
	cout << "Enter First Number: " << endl;
	cin >> in1;
	cout << "Enter Second Number: " << endl;
	cin >> in2;
	cout << "Enter Third Number: " << endl;
	cin >> in3;

	//Assing input to dummy readings
	readings.push_back(in1);
	readings.push_back(in2);
	readings.push_back(in3);

	double correctedValue = correctTuple(readings);

	cout << correctedValue << endl;

	system("pause");

	return 0;
}