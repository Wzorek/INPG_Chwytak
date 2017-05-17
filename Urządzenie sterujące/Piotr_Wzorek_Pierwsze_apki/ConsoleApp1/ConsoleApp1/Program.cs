using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HalloWorld
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World");
            Console.WriteLine("Podaj nazwisko programisty");
            string Wzorek = Console.ReadLine();
            Console.WriteLine(Wzorek);
            Console.WriteLine("Podaj ile on ma lat");
            var  Number = Console.ReadLine();
            Console.WriteLine("On ma {0} lat", Number);
            Console.WriteLine("Click any button to end...");
            Console.ReadLine();
        }
    }
}
