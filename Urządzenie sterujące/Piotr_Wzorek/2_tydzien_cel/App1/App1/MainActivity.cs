using Android.App;
using Android.Widget;
using Android.OS;

namespace App1
{
    [Activity(Label = "Super APKa", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : Activity
    {
        protected override void OnCreate(Bundle bundle)
        {
            int count = 1;
            bool switchStatus = false;
            base.OnCreate(bundle);
            // Set our view from the "main" layout resourcevi
            SetContentView(Resource.Layout.Main);
            var switch1 = FindViewById<Switch>(Resource.Id.switch1);
            TextView textSwitch = FindViewById<TextView>(Resource.Id.textView3);
            switch1.Checked = true;
            switch1.CheckedChange += (s, b) =>
            {
                bool IsOn = b.IsChecked;
                if (IsOn)
                {
                    textSwitch.Text = "Przełącznik jest ustawiony na true";
                }
                else
                {
                    textSwitch.Text = "Przełącznik jest ustawiony na false";
                };
            };
            var seekBar = FindViewById<SeekBar>(Resource.Id.seekBar1);
            var seekbarResult = FindViewById<TextView>(Resource.Id.seekbarResult);
            seekBar.ProgressChanged += (s, e) =>
            {
                seekbarResult.Text = string.Format("{0}", e.Progress);
            };
            Button button1 = FindViewById<Button>(Resource.Id.button1);
            button1.Click += delegate { button1.Text = string.Format("{0} clicks!", count++); };
        }
    }
}

